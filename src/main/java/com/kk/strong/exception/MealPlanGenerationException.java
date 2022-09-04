package com.kk.strong.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MealPlanGenerationException extends ResponseStatusException {

    public MealPlanGenerationException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Error generating the meal plan");
    }
}
