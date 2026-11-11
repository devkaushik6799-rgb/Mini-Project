package com.medicare.config;

import com.medicare.medicine.Medicine;
import com.medicare.medicine.MedicineRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seedMedicines(MedicineRepository repository) {
        return args -> {
            if (repository.count() > 0) return;
            repository.saveAll(List.of(
                new Medicine("Paracetamol 500 mg", "Pain Relief", "Common over-the-counter pain and fever relief. Follow the product label and professional advice.", new BigDecimal("22.00"), 80, false),
                new Medicine("Vitamin C Tablets", "Wellness", "Vitamin C supplement. Use according to label directions and professional advice.", new BigDecimal("95.00"), 45, false),
                new Medicine("Antiseptic Cream", "First Aid", "Topical antiseptic first-aid product for minor skin care. Follow package directions.", new BigDecimal("68.00"), 32, false),
                new Medicine("Oral Rehydration Salts", "Digestive Health", "Oral rehydration product. Prepare exactly as directed on the packet.", new BigDecimal("24.00"), 60, false),
                new Medicine("Cetirizine 10 mg", "Wellness", "Antihistamine product. Check warnings and consult a pharmacist before use.", new BigDecimal("18.00"), 40, false),
                new Medicine("Amoxicillin 500 mg", "Wellness", "Prescription-only antibiotic listing for workflow demonstration; not available for demo checkout.", new BigDecimal("75.00"), 20, true),
                new Medicine("Digital Thermometer", "First Aid", "Reusable digital thermometer for temperature measurement.", new BigDecimal("145.00"), 25, false),
                new Medicine("Calcium Tablets", "Wellness", "Calcium supplement listing. Ask a healthcare professional whether it is appropriate for you.", new BigDecimal("110.00"), 30, false)
            ));
        };
    }
}
