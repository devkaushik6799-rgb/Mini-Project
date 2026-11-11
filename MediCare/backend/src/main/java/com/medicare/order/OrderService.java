package com.medicare.order;

import com.medicare.common.ResourceNotFoundException;
import com.medicare.medicine.Medicine;
import com.medicare.medicine.MedicineRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class OrderService {
    private final MedicineRepository medicines;
    private final OrderRepository orders;

    public OrderService(MedicineRepository medicines, OrderRepository orders) {
        this.medicines = medicines;
        this.orders = orders;
    }

    @Transactional
    public OrderEntity create(OrderDtos.CreateOrderRequest request) {
        // Merge duplicate medicine lines before stock validation and deduction.
        Map<Long, Integer> quantities = new LinkedHashMap<>();
        for (OrderDtos.ItemRequest item : request.items()) {
            quantities.merge(item.medicineId(), item.quantity(), Integer::sum);
        }

        BigDecimal total = BigDecimal.ZERO;
        Map<Medicine, Integer> resolved = new LinkedHashMap<>();
        for (Map.Entry<Long, Integer> entry : quantities.entrySet()) {
            if (entry.getValue() > 20) {
                throw new IllegalArgumentException("Maximum quantity per medicine is 20");
            }
            Medicine medicine = medicines.findByIdForUpdate(entry.getKey())
                    .orElseThrow(() -> new ResourceNotFoundException("Medicine not found: " + entry.getKey()));
            int quantity = entry.getValue();
            if (medicine.isPrescriptionRequired()) {
                throw new IllegalArgumentException("Prescription review required for " + medicine.getName()
                        + "; demo checkout cannot dispense it.");
            }
            if (medicine.getStockQuantity() < quantity) {
                throw new IllegalArgumentException("Insufficient stock for " + medicine.getName());
            }
            resolved.put(medicine, quantity);
            total = total.add(medicine.getPrice().multiply(BigDecimal.valueOf(quantity)));
        }

        OrderEntity order = new OrderEntity(request.customerName().trim(), request.customerEmail().trim(), total);
        for (Map.Entry<Medicine, Integer> entry : resolved.entrySet()) {
            Medicine medicine = entry.getKey();
            int quantity = entry.getValue();
            medicine.setStockQuantity(medicine.getStockQuantity() - quantity);
            order.addItem(new OrderItem(medicine.getId(), medicine.getName(), quantity, medicine.getPrice()));
        }
        return orders.save(order);
    }
}
