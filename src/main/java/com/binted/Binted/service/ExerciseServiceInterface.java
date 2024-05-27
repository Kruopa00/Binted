package com.binted.Binted.service;

import com.binted.Binted.dto.ExerciseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExerciseServiceInterface {
    public ExerciseDto getExerciseById(Long id);

    public ExerciseDto createExercise(ExerciseDto request);

    public void deleteExercise(Long id);

    Page<ExerciseDto> getAllExercises(Pageable pageable);

    public ExerciseDto updateExercise(Long id, ExerciseDto request);
}
