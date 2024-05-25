package com.binted.Binted.controller;

import com.binted.Binted.dto.ExerciseDto;
import com.binted.Binted.service.ExerciseService;
import jakarta.persistence.OptimisticLockException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@RestController
@AllArgsConstructor
@SessionAttributes("exercise")
@SessionScope
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping("/exercise")
    public ResponseEntity<ExerciseDto> getExerciseAndLogs(@RequestParam(value = "id") Long id) {
        ExerciseDto exerciseDto = exerciseService.getExerciseById(id);
        return ResponseEntity.ok(exerciseDto);
    }

    @PostMapping("/exercise")
    public ResponseEntity<ExerciseDto> createExercise(@RequestBody ExerciseDto request) {
        ExerciseDto createdExercise = exerciseService.createExercise(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExercise);
    }

    @GetMapping("/exercises")
    public ResponseEntity<List<ExerciseDto>> getAllExercises() {
        List<ExerciseDto> exercises = exerciseService.getAllExercises();
        return ResponseEntity.ok(exercises);
    }

    @PutMapping("/exercise/{id}")
    public ResponseEntity<Object> updateExercise(@PathVariable Long id, @RequestBody ExerciseDto request) {
        ExerciseDto updatedExercise = null;
        try {
            updatedExercise = exerciseService.updateExercise(id, request);
            return ResponseEntity.ok(updatedExercise);
        } catch (OptimisticLockException | ObjectOptimisticLockingFailureException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict occurred while updating. Please decide whether to cancel the changes or overwrite them.");
        }
    }

    @ModelAttribute("exercise")
    public ExerciseDto getExerciseSession() {
        return new ExerciseDto();
    }
}
