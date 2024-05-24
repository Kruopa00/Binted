package com.binted.Binted.service;

import com.binted.Binted.dto.ExerciseDto;
import com.binted.Binted.dto.RecordDto;
import com.binted.Binted.entity.ExerciseEntity;
import com.binted.Binted.entity.RecordEntity;
import com.binted.Binted.mapper.ExerciseMapper;
import com.binted.Binted.mapper.RecordMapper;
import com.binted.Binted.repository.ExerciseRepository;
import com.binted.Binted.repository.RecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequestScope
@AllArgsConstructor
public class ExerciseService implements ExerciseServiceInterface {

    private final RecordRepository recordRepository;
    private final ExerciseRepository exerciseRepository;

    @Override
    public ExerciseDto getExerciseById(Long id) {
        ExerciseEntity exercise = exerciseRepository.findById(id).orElse(null);
        List<RecordEntity> records = recordRepository.findByExercise(exercise);
        return ExerciseMapper.mapToExerciseDto(exercise, records);
    }

    @Override
    public ExerciseDto createExercise(ExerciseDto request) {
        ExerciseEntity exerciseEntity = new ExerciseEntity();
        exerciseEntity.setName(request.getName());
        exerciseEntity.setGoal(request.getGoal());
        exerciseRepository.save(exerciseEntity);
        return ExerciseMapper.mapToExerciseDto(exerciseEntity, Collections.emptyList());
    }

    @Override
    public List<ExerciseDto> getAllExercises() {
        List<ExerciseEntity> exercises = exerciseRepository.findAll();
        return exercises.stream().map(exercise -> {
            List<RecordEntity> records = recordRepository.findByExercise(exercise);
            return ExerciseMapper.mapToExerciseDto(exercise, records);
        }).collect(Collectors.toList());
    }

    @Override
    public ExerciseDto updateExercise(Long id, ExerciseDto request) {
        Optional<ExerciseEntity> optionalExercise = exerciseRepository.findById(id);
        if (optionalExercise.isPresent()) {
            ExerciseEntity exerciseEntity = optionalExercise.get();
            exerciseEntity.setName(request.getName());
            exerciseEntity.setGoal(request.getGoal());
            exerciseRepository.save(exerciseEntity);
            List<RecordEntity> records = recordRepository.findByExercise(exerciseEntity);
            return ExerciseMapper.mapToExerciseDto(exerciseEntity, records);
        } else {
            throw new RuntimeException("Exercise not found");
        }
    }
}