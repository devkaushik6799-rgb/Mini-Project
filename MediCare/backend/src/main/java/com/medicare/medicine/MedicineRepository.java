package com.medicare.medicine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;
import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    List<Medicine> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrCategoryContainingIgnoreCase(
            String name, String description, String category);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select m from Medicine m where m.id = :id")
    java.util.Optional<Medicine> findByIdForUpdate(@Param("id") Long id);
}
