package com.binted.Binted.mapper;

import com.binted.Binted.dto.ExerciseDto;
import com.binted.Binted.entity.ExerciseEntity;
import com.binted.Binted.entity.RecordEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.binted.Binted.mapper.RecordMapper.mapToRecordDto;

public class ExerciseMapper {

    public static ExerciseDto mapToExerciseDto(ExerciseEntity exercise, List<RecordEntity> records){
        return new ExerciseDto(
                exercise.getId(),
                exercise.getName(),
                exercise.getGoal(),
                records.stream().map((record) -> RecordMapper.mapToRecordDto(record)).collect(Collectors.toList())
        );
    }

}
