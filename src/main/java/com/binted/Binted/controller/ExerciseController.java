package com.binted.Binted.controller;


import com.binted.Binted.dto.ExerciseDto;
import com.binted.Binted.mapper.ExerciseMapper;
import com.binted.Binted.repository.RecordRepository;
import com.binted.Binted.service.ExerciseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import java.util.List;

@RestController
@AllArgsConstructor
@SessionScope
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final RecordRepository recordRepository;


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
}

