package com.kk.strong.controller;

import com.kk.strong.model.Exercise;
import com.kk.strong.model.WorkoutSession;
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
public class WorkoutSessionController {

    private final WorkoutSessionService workoutSessionService;

    @GetMapping("/workout_sessions")
    public ResponseEntity<List<WorkoutSession>> getWorkoutSessions() {
        return ResponseEntity.ok(workoutSessionService.getWorkoutSessions());
    }

    @GetMapping("/workout_sessions/{workoutSessionId}")
    public ResponseEntity<WorkoutSession> getWorkoutSession(@PathVariable Long workoutSessionId) {
        return ResponseEntity.ok(workoutSessionService.getWorkoutSession(workoutSessionId));
    }

    @PutMapping("/workout_sessions/{workoutSessionId}")
    public ResponseEntity<?> updateWorkoutSession(@PathVariable Long workoutSessionId, @RequestBody WorkoutSession workoutSession) {
        workoutSessionService.updateWorkoutSession(workoutSessionId, workoutSession);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/workout_sessions/{workoutSessionId}")
    public ResponseEntity<?> deleteWorkoutSession(@PathVariable Long workoutSessionId) {
        workoutSessionService.deleteWorkoutSession(workoutSessionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/workout_sessions/{workoutSessionId}/exercises")
    public ResponseEntity<List<Exercise>> getExercisesForWorkoutSession(@PathVariable Long workoutSessionId) {
        return ResponseEntity.ok(workoutSessionService.getExercisesForWorkoutSession(workoutSessionId));
    }

    @PostMapping("/workout_sessions/{workoutSessionId}/exercises")
    public ResponseEntity<Exercise> addExerciseForWorkoutSession(@PathVariable Long workoutSessionId, @RequestBody Exercise exercise) {
        return ResponseEntity.created(URI.create(String.format("/workout_sessions/%s", workoutSessionId)))
                .body(workoutSessionService.addExerciseForWorkoutSession(workoutSessionId, exercise));
    }
}
