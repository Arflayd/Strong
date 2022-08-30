package com.kk.strong.model.dto;

import com.kk.strong.model.WorkoutCategory;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class WorkoutSessionDto {

    private Long id;
    private LocalDateTime timestamp;
    private WorkoutCategory workoutCategory;
    private List<ExerciseDto> exercises = new ArrayList<>();
}
