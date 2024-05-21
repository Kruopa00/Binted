package com.binted.Binted.mapper;

import com.binted.Binted.dto.ExerciseDto;
import com.binted.Binted.dto.RecordDto;
import com.binted.Binted.entity.ExerciseEntity;
import com.binted.Binted.entity.RecordEntity;

public class RecordMapper {
    public static RecordEntity mapToRecordEntity(RecordDto record){
        return new RecordEntity(
                record.getId(),
                record.getDate(),
                record.getWeight(),
                record.getReps(),
                record.getSets(),
                record.getComment(),
                null
        );
    }

    public static RecordDto mapToRecordDto(RecordEntity record){
        return new RecordDto(
                record.getId(),
                record.getDate(),
                record.getWeight(),
                record.getReps(),
                record.getSets(),
                record.getComment(),
                record.getExercise().getId()
        );
    }
}
