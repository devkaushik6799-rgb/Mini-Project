
package com.medicare.medicine;

import com.medicare.common.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/medicines")
public class MedicineController {
    private final MedicineRepository repository;

    public MedicineController(MedicineRepository repository) { this.repository = repository; }

    @GetMapping
    public List<Medicine> all(@RequestParam(required = false) String search) {
        if (search == null || search.isBlank()) return repository.findAll();
        String term = search.trim();
        return repository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrCategoryContainingIgnoreCase(
                term, term, term);
    }

    @GetMapping("/{id}")
    public Medicine one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicine not found: " + id));
    }
}
