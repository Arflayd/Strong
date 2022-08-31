package com.kk.strong.controller;

import com.kk.strong.model.Exercise;
import com.kk.strong.model.WorkoutSession;
import com.kk.strong.model.dto.ExerciseDto;
import com.kk.strong.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('REGULAR_USER')")
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping
    public ResponseEntity<List<ExerciseDto>> getExercises() {
        return ResponseEntity.ok(exerciseService.getExercises());
    }

    @GetMapping("/{exerciseId}")
    public ResponseEntity<ExerciseDto> getExercise(@PathVariable Long exerciseId) {
        return ResponseEntity.ok(exerciseService.getExercise(exerciseId));
    }

    @PutMapping("/{exerciseId}")
    public ResponseEntity<?> updateExercise(@PathVariable Long exerciseId, @RequestBody ExerciseDto exerciseDto) {
        exerciseService.updateExercise(exerciseId, exerciseDto);
        return ResponseEntity.ok().build();
    }
}