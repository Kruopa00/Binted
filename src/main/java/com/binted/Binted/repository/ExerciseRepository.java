package com.binted.Binted.repository;

import com.binted.Binted.entity.ExerciseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {
    @Query("select e from exercise e where e.id = :id")
    ExerciseEntity findExerciseById(@Param("id") Long id);

    Page<ExerciseEntity> findAll(Pageable pageable);
}
