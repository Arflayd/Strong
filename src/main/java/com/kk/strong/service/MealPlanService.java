package com.kk.strong.service;

import com.kk.strong.exception.MealPlanGenerationException;
import com.kk.strong.model.dto.MealPlanDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class MealPlanService {

    private final WebClient mealApiClient;

    public MealPlanDto generateMealPlan(int targetCalories) {
        log.info("Generating a meal plan with target calories: {}", targetCalories);
        try {
            return mealApiClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("timeFrame", "day")
                            .build())
                    .retrieve()
                    .bodyToMono(MealPlanDto.class)
                    .block();
        } catch (Exception e) {
            throw new MealPlanGenerationException();
        }
    }
}
