package com.kk.strong.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BodyReportDto {

    private Long id;
    private LocalDateTime timestamp;
    private double weight;
    private double muscleWeight;
    private double bodyFat;
    private double waterLevel;
    private double proteinLevel;
}
