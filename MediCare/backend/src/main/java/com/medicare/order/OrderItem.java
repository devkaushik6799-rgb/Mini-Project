package com.medicare.order;
import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity @Table(name="order_items")
public class OrderItem {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="order_id",nullable=false) private OrderEntity order;
 @Column(nullable=false) private Long medicineId;
 @Column(nullable=false) private String medicineName;
 @Column(nullable=false) private Integer quantity;
 @Column(nullable=false,precision=10,scale=2) private BigDecimal unitPrice;
 protected OrderItem(){}
 public OrderItem(Long medicineId,String medicineName,Integer quantity,BigDecimal unitPrice){this.medicineId=medicineId;this.medicineName=medicineName;this.quantity=quantity;this.unitPrice=unitPrice;}
 void setOrder(OrderEntity order){this.order=order;}
 public Long getId(){return id;}
 public Long getMedicineId(){return medicineId;}
 public String getMedicineName(){return medicineName;}
 public Integer getQuantity(){return quantity;}
 public BigDecimal getUnitPrice(){return unitPrice;}
}
