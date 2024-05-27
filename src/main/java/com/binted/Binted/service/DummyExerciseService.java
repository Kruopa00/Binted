package com.binted.Binted.service;

import com.binted.Binted.dto.ExerciseDto;
import com.binted.Binted.entity.ExerciseEntity;
import com.binted.Binted.entity.RecordEntity;
import com.binted.Binted.mapper.ExerciseMapper;
import com.binted.Binted.repository.ExerciseRepository;
import com.binted.Binted.repository.RecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Qualifier("dummyExerciseService")
public class DummyExerciseService implements ExerciseServiceInterface{

    private final RecordRepository recordRepository;
    private final ExerciseRepository exerciseRepository;

    @Override
    public ExerciseDto getExerciseById(Long id) {
        ExerciseDto exerciseDto = new ExerciseDto(1L, "dummy exercise", "dummy goal", null);
        return exerciseDto;
    }

    @Override
    public ExerciseDto createExercise(ExerciseDto request) {
        ExerciseDto exerciseDto = new ExerciseDto(1L, "dummy exercise", "dummy goal", null);
        return exerciseDto;
    }

    @Override
    public void deleteExercise(Long id){
        return;
    }

    @Override
    public List<ExerciseDto> getAllExercises() {
        List<ExerciseDto> exercises = new ArrayList<>();
        ExerciseDto exerciseDto = new ExerciseDto(1L, "dummy exercise", "dummy goal", null);
        ExerciseDto exerciseDto2 = new ExerciseDto(2L, "dummy exercise2", "dummy goal2", null);
        exercises.add(exerciseDto);
        exercises.add(exerciseDto2);

        return exercises;
    }

    @Override
    public ExerciseDto updateExercise(Long id, ExerciseDto request){
        ExerciseDto exerciseDto = new ExerciseDto(1L, "dummy exercise", "dummy goal", null);
        return exerciseDto;
    }
}