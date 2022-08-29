package com.kk.strong.controller;

import com.kk.strong.model.Exercise;
import com.kk.strong.model.WorkoutSession;
import com.kk.strong.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('REGULAR_USER')")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping("/exercises")
    public ResponseEntity<List<Exercise>> getExercises() {
        return ResponseEntity.ok(exerciseService.getExercises());
    }

    @GetMapping("/exercises/{exerciseId}")
    public ResponseEntity<Exercise> getExercise(@PathVariable Long exerciseId) {
        return ResponseEntity.ok(exerciseService.getExercise(exerciseId));
    }

    @PutMapping("/exercises/{exerciseId}")
    public ResponseEntity<?> updateExercise(@PathVariable Long exerciseId, @RequestBody Exercise exercise) {
        exerciseService.updateExercise(exerciseId, exercise);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/exercises/{exerciseId}")
    public ResponseEntity<?> deleteExercise(@PathVariable Long exerciseId) {
        exerciseService.deleteExercise(exerciseId);
        return ResponseEntity.noContent().build();
    }
}
