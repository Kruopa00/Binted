package com.binted.Binted.repository;

import com.binted.Binted.entity.ExerciseEntity;
import com.binted.Binted.entity.RecordEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RecordRepository extends JpaRepository<RecordEntity, Long> {
    @Query("select r from record r where r.exercise = :exerciseId")
    List<RecordEntity> findByExercise(@Param("exerciseId") ExerciseEntity exerciseId);

    //@Query(value = "select * from record where exercise_id = :exerciseId order by id desc limit 3", nativeQuery = true)
    //List<RecordEntity> findMostRecentByExercise(@Param("exerciseId") Long exerciseId);
}