package com.kk.strong.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ExerciseNotFoundException extends ResponseStatusException {

    public ExerciseNotFoundException(Long exerciseId) {
        super(HttpStatus.NOT_FOUND, String.format("Exercise not found with id: %s", exerciseId));
    }
}
