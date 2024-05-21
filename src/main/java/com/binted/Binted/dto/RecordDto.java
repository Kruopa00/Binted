package com.binted.Binted.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecordDto {
    private Long id;
    private String date;
    private Double weight;
    private Integer reps;
    private Integer sets;
    private String comment;
    private Long exerciseId;
}
