package com.binted.Binted.repository;

import com.binted.Binted.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {
    @Query("select e from exercise e where e.id = :id")
    ExerciseEntity findExerciseById(@Param("id") Long id);
}
