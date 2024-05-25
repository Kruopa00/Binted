package com.binted.Binted.controller;

import com.binted.Binted.dto.ExerciseDto;
import com.binted.Binted.service.ExerciseService;
import com.binted.Binted.service.ExerciseServiceInterface;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@RestController
@AllArgsConstructor
@SessionAttributes("exercise")
@SessionScope
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
    public ResponseEntity<List<ExerciseDto>> getAllExercises() {
        List<ExerciseDto> exercises = exerciseService.getAllExercises();
        return ResponseEntity.ok(exercises);
    }

    @PutMapping("/exercise/{id}")
    public ResponseEntity<ExerciseDto> updateExercise(@PathVariable Long id, @RequestBody ExerciseDto request) {
        ExerciseDto updatedExercise = exerciseService.updateExercise(id, request);
        return ResponseEntity.ok(updatedExercise);
    }

    @ModelAttribute("exercise")
    public ExerciseDto getExerciseSession() {
        return new ExerciseDto();
    }
}
