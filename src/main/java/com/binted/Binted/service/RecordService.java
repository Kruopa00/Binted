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

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class RecordService implements RecordServiceInterface{

    private final RecordRepository recordRepository;
    private final ExerciseService exerciseService;
    private final ExerciseRepository exerciseRepository;

    public RecordDto createRecord(RecordDto record) {
        RecordEntity recordEntity = RecordMapper.mapToRecordEntity(record);

        ExerciseEntity exercise = exerciseRepository.getReferenceById(record.getExerciseId());
        recordEntity.setExercise(exercise);

        recordEntity = recordRepository.save(recordEntity);

        return RecordMapper.mapToRecordDto(recordEntity);
    }
}