package com.kk.strong.controller;

import com.kk.strong.model.ExerciseType;
import com.kk.strong.model.RankingSortingType;
import com.kk.strong.model.dto.BodyReportDto;
import com.kk.strong.model.dto.ExerciseDto;
import com.kk.strong.model.dto.GymUserDto;
import com.kk.strong.model.dto.WorkoutSessionDto;
import com.kk.strong.service.GymUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('REGULAR_USER')")
@RequestMapping("/gym_users")
public class GymUserController {

    private final GymUserService gymUserService;

    @GetMapping
    public ResponseEntity<List<GymUserDto>> getGymUsers() {
        return ResponseEntity.ok(gymUserService.getUsers());
    }

    @GetMapping("/{gymUserId}")
    public ResponseEntity<GymUserDto> getGymUser(@PathVariable Long gymUserId) {
        return ResponseEntity.ok(gymUserService.getUser(gymUserId));
    }

    @PutMapping("/{gymUserId}")
    @PreAuthorize("hasAuthority('PREMIUM_USER')")
    public ResponseEntity<GymUserDto> updateUser(@PathVariable Long gymUserId, @RequestBody GymUserDto gymUserDto) {
        return ResponseEntity.created(URI.create("/users")).body(gymUserService.updateUser(gymUserId, gymUserDto));
    }

    @DeleteMapping("/{gymUserId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long gymUserId) {
        gymUserService.deleteUser(gymUserId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{gymUserId}/body_reports")
    public ResponseEntity<BodyReportDto> addBodyReportForUser(@PathVariable Long gymUserId, @RequestBody BodyReportDto bodyReportDto) {
        return ResponseEntity.created(URI.create(String.format("/gym_users/%s/body_reports", gymUserId)))
                .body(gymUserService.addBodyReportForUser(gymUserId, bodyReportDto));
    }

    @PostMapping("/{gymUserId}/workout_sessions")
    public ResponseEntity<WorkoutSessionDto> addWorkoutSessionForUser(@PathVariable Long gymUserId, @RequestBody WorkoutSessionDto workoutSessionDto) {
        return ResponseEntity.created(URI.create(String.format("/users/%s/workout_sessions", gymUserId)))
                .body(gymUserService.addWorkoutSessionForUser(gymUserId, workoutSessionDto));
    }

    @GetMapping("/rankings/{exerciseType}/{rankingSortingType}")
    public ResponseEntity<List<ExerciseDto>> getExercisesForUserAndExerciseType(@PathVariable ExerciseType exerciseType, @PathVariable RankingSortingType rankingSortingType) {
        return ResponseEntity.ok(gymUserService.getLatestExercisesOfExerciseTypeForAllUsersWithSorting(exerciseType, rankingSortingType));
    }
}
