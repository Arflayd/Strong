package com.kk.strong.controller;

import com.kk.strong.model.dto.MealPlanDto;
import com.kk.strong.service.MealPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('PREMIUM_USER')")
@RequestMapping("/meals")
public class MealPlanController {

    private final MealPlanService mealPlanService;

    @GetMapping("/generate")
    public ResponseEntity<MealPlanDto> generateMealPlan(@RequestParam int targetCalories) {
        return ResponseEntity.ok(mealPlanService.generateMealPlan(targetCalories));
    }
}
