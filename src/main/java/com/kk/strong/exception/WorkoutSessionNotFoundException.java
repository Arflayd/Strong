package com.kk.strong.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WorkoutSessionNotFoundException extends ResponseStatusException {

    public WorkoutSessionNotFoundException(Long workoutSessionId) {
        super(HttpStatus.NOT_FOUND, String.format("Workout session not found with id: %s", workoutSessionId));
    }
}
