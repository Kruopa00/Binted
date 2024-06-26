package com.binted.Binted.controller;


import com.binted.Binted.dto.ExerciseDto;
import com.binted.Binted.dto.RecordDto;
import com.binted.Binted.service.ExerciseService;
import com.binted.Binted.service.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
public class RecordController {

    @Autowired
    private final RecordService recordService;

    @PostMapping("/record")
    public ResponseEntity<RecordDto> createRecord(@RequestBody RecordDto record) {
        RecordDto createdRecord = recordService.createRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecord);
    }
}

