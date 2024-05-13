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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExerciseService implements ExerciseServiceInterface{

    private final RecordRepository recordRepository;
    private ExerciseRepository exerciseRepository;

    public ExerciseDto getExerciseById(Long id) {
        ExerciseEntity exercise = exerciseRepository.findById(id).orElse(null);

        List<RecordEntity> records = recordRepository.findByExercise(exercise);

        return ExerciseMapper.mapToExerciseDto(exercise, records);
    }
}