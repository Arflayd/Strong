package com.kk.strong.model.dto;

import com.kk.strong.model.ExerciseType;
import lombok.Data;

@Data
public class ExerciseDto {

    private Long id;
    private Long workoutSessionId;
    private ExerciseType exerciseType;
    private int sets;
    private int repetitions;
    private int loadWeight;
}
