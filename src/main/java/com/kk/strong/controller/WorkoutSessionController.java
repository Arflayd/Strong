package com.kk.strong.controller;

import com.kk.strong.model.Exercise;
import com.kk.strong.model.WorkoutSession;
import com.kk.strong.model.dto.ExerciseDto;
import com.kk.strong.model.dto.WorkoutSessionDto;
import com.kk.strong.service.WorkoutSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('REGULAR_USER')")
@RequestMapping("/workout_sessions")
public class WorkoutSessionController {

    private final WorkoutSessionService workoutSessionService;

    @GetMapping
    public ResponseEntity<List<WorkoutSessionDto>> getWorkoutSessions() {
        return ResponseEntity.ok(workoutSessionService.getWorkoutSessions());
    }

    @GetMapping("/{workoutSessionId}")
    public ResponseEntity<WorkoutSessionDto> getWorkoutSession(@PathVariable Long workoutSessionId) {
        return ResponseEntity.ok(workoutSessionService.getWorkoutSession(workoutSessionId));
    }

    @PutMapping("/{workoutSessionId}")
    public ResponseEntity<?> updateWorkoutSession(@PathVariable Long workoutSessionId, @RequestBody WorkoutSessionDto workoutSessionDto) {
        workoutSessionService.updateWorkoutSession(workoutSessionId, workoutSessionDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{workoutSessionId}/exercises")
    public ResponseEntity<ExerciseDto> addExerciseForWorkoutSession(@PathVariable Long workoutSessionId, @RequestBody ExerciseDto exerciseDto) {
        return ResponseEntity.created(URI.create(String.format("/workout_sessions/%s", workoutSessionId)))
                .body(workoutSessionService.addExerciseForWorkoutSession(workoutSessionId, exerciseDto));
    }

    @DeleteMapping("/{workoutSessionId}/exercises/{exerciseId}")
    public ResponseEntity<?> deleteExerciseForWorkoutSession(@PathVariable Long workoutSessionId, @PathVariable Long exerciseId) {
        workoutSessionService.deleteExerciseForWorkoutSession(workoutSessionId, exerciseId);
        return ResponseEntity.noContent().build();
    }
}
