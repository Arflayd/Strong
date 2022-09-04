package com.kk.strong.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(using = MealPlanDtoDeserializer.class)
@AllArgsConstructor
public class MealPlanDto {

    private String breakfast;
    private String lunch;
    private String dinner;

    private double calories;
    private double protein;
    private double fat;
    private double carbohydrates;
}
