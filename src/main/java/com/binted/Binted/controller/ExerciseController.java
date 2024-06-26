package com.binted.Binted.controller;

import com.binted.Binted.dto.ExerciseDto;
import com.binted.Binted.service.ExerciseService;
import jakarta.persistence.OptimisticLockException;
import com.binted.Binted.service.ExerciseServiceInterface;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
public class ExerciseController {

    @Autowired
    @Qualifier("exerciseService")
    private final ExerciseServiceInterface exerciseService;

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

    @DeleteMapping("/exercise")
    public ResponseEntity<String> deleteExercise(@RequestParam(value = "id") Long id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/exercises")
    public ResponseEntity<Page<ExerciseDto>> getAllExercises(@PageableDefault(size = 5) Pageable pageable) {
        Page<ExerciseDto> exercises = exerciseService.getAllExercises(pageable);
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
