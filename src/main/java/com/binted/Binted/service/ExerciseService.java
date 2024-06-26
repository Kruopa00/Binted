package com.binted.Binted.service;

import com.binted.Binted.dto.ExerciseDto;
import com.binted.Binted.entity.ExerciseEntity;
import com.binted.Binted.entity.RecordEntity;
import com.binted.Binted.mapper.ExerciseMapper;
import com.binted.Binted.repository.ExerciseRepository;
import com.binted.Binted.repository.RecordRepository;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("exerciseService")
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
    public void deleteExercise(Long id){
        exerciseRepository.deleteById(id);
    }

    @Override
    public Page<ExerciseDto> getAllExercises(Pageable pageable) {
        Page<ExerciseEntity> exercises = exerciseRepository.findAll(pageable);
        return exercises.map(exercise -> {
            List<RecordEntity> records = recordRepository.findMostRecentByExercise(exercise);
            return ExerciseMapper.mapToExerciseDto(exercise, records);
        });
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
                List<RecordEntity> records = recordRepository.findByExercise(exerciseEntity);

                return ExerciseMapper.mapToExerciseDto(exerciseEntity, records);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (OptimisticLockException | ObjectOptimisticLockingFailureException e) {
                throw new RuntimeException("Optimistic locking failure: " + e.getMessage(), e);
            }
        } else {
            throw new RuntimeException("Exercise not found");
        }
    }
}