package com.kk.strong.controller;

import com.kk.strong.model.BodyReport;
import com.kk.strong.model.WorkoutSession;
import com.kk.strong.model.dto.BodyReportDto;
import com.kk.strong.model.dto.UserDto;
import com.kk.strong.model.dto.WorkoutSessionDto;
import com.kk.strong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('REGULAR_USER')")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> registerUser(
            @PathParam("username") String username,
            @PathParam("password") String password,
            @RequestBody UserDto userDto) {
        return ResponseEntity.created(URI.create("/users")).body(userService.registerUser(username, password, userDto));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('PREMIUM_USER')")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        return ResponseEntity.created(URI.create("/users")).body(userService.updateUser(userId, userDto));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/body_reports")
    public ResponseEntity<BodyReportDto> addBodyReportForUser(@PathVariable Long userId, @RequestBody BodyReportDto bodyReportDto) {
        return ResponseEntity.created(URI.create(String.format("/users/%s/body_reports", userId)))
                .body(userService.addBodyReportForUser(userId, bodyReportDto));
    }

    @DeleteMapping("/{userId}/body_reports/{bodyReportId}")
    public ResponseEntity<?> deleteBodyReportForUser(@PathVariable Long userId, @PathVariable Long bodyReportId) {
        userService.deleteBodyReportForUser(userId, bodyReportId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/workout_sessions")
    public ResponseEntity<WorkoutSessionDto> addWorkoutSessionForUser(@PathVariable Long userId, @RequestBody WorkoutSessionDto workoutSessionDto) {
        return ResponseEntity.created(URI.create(String.format("/users/%s/workout_sessions", userId)))
                .body(userService.addWorkoutSessionForUser(userId, workoutSessionDto));
    }

    @DeleteMapping("/{userId}/workout_sessions/{workoutSessionId}")
    public ResponseEntity<?> deleteWorkoutSessionForUser(@PathVariable Long userId, @PathVariable Long workoutSessionId) {
        userService.deleteWorkoutSessionForUser(userId, workoutSessionId);
        return ResponseEntity.noContent().build();
    }
}
