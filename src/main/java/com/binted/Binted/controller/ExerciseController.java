package com.binted.Binted.controller;


import com.binted.Binted.dto.ExerciseDto;
import com.binted.Binted.service.ExerciseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
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
}

