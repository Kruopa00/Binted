package com.binted.Binted.service;

import com.binted.Binted.dto.ExerciseDto;

import java.util.List;

public interface ExerciseServiceInterface {
    public ExerciseDto getExerciseById(Long id);

    public ExerciseDto createExercise(ExerciseDto request);

    public List<ExerciseDto> getAllExercises();

    public ExerciseDto updateExercise(Long id, ExerciseDto request);
}
