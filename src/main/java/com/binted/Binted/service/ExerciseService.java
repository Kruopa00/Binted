package com.binted.Binted.service;

import com.binted.Binted.dto.ExerciseDto;
import com.binted.Binted.dto.RecordDto;
import com.binted.Binted.entity.ExerciseEntity;
import com.binted.Binted.entity.RecordEntity;
import com.binted.Binted.mapper.ExerciseMapper;
import com.binted.Binted.mapper.RecordMapper;
import com.binted.Binted.repository.ExerciseRepository;
import com.binted.Binted.repository.RecordRepository;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.StaleObjectStateException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
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

    @Transactional
    public ExerciseDto updateExercise(Long id, ExerciseDto request) {
        Optional<ExerciseEntity> optionalExercise = exerciseRepository.findById(id);
        if (optionalExercise.isPresent()) {
            ExerciseEntity exerciseEntity = optionalExercise.get();
            exerciseEntity.setName(request.getName());
            exerciseEntity.setGoal(request.getGoal());
            try {
                Thread.sleep(5000); // Simulate delay to provoke concurrent modification
                exerciseRepository.save(exerciseEntity);
                System.out.println("lololo1");

                List<RecordEntity> records = recordRepository.findByExercise(exerciseEntity);
                System.out.println("lololo2");

                return ExerciseMapper.mapToExerciseDto(exerciseEntity, records);
            } catch (InterruptedException e) {
                System.out.println("lololo3");
                throw new RuntimeException(e);
            } catch (OptimisticLockException | ObjectOptimisticLockingFailureException e) {
                System.out.println("lololo4");
                // Handle the exception, possibly retry the operation or return a specific response
                throw new RuntimeException("Optimistic locking failure: " + e.getMessage(), e);
            } catch (Exception exception) {
                System.out.println("lololo4.2");
                throw new RuntimeException("Optimistic locking failure: " + exception.getMessage(), exception);
            }
        } else {
            System.out.println("lololo5");
            throw new RuntimeException("Exercise not found");
        }
    }
}