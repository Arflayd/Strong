package com.kk.strong.controller;

import com.kk.strong.model.BodyReport;
import com.kk.strong.model.WorkoutSession;
import com.kk.strong.model.dto.UserDto;
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
public class UserController {

    private final UserService userService;

    @PostMapping(path = "/users")
    public ResponseEntity<UserDto> registerUser(
            @PathParam("username") String username,
            @PathParam("password") String password,
            @RequestBody UserDto userDto) {
        return ResponseEntity.created(URI.create("/users")).body(userService.registerUser(username, password, userDto));
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping(path = "/users/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PutMapping(path = "/users/{userId}")
    @PreAuthorize("hasAuthority('PREMIUM_USER')")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        return ResponseEntity.created(URI.create("/users")).body(userService.updateUser(userId, userDto));
    }

    @DeleteMapping(path = "/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/users/{userId}/body_reports")
    public ResponseEntity<List<BodyReport>> getBodyReportsForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getBodyReportsForUser(userId));
    }

    @GetMapping(path = "/users/{userId}/workout_sessions")
    public ResponseEntity<List<WorkoutSession>> getWorkoutSessionsForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getWorkoutSessionsForUser(userId));
    }

    @PostMapping(path = "/users/{userId}/body_reports")
    public ResponseEntity<BodyReport> addBodyReportForUser(@PathVariable Long userId, @RequestBody BodyReport bodyReport) {
        return ResponseEntity.created(URI.create(String.format("/users/%s/body_reports", userId)))
                .body(userService.addBodyReportForUser(userId, bodyReport));
    }

    @PostMapping(path = "/users/{userId}/workout_sessions")
    public ResponseEntity<WorkoutSession> addWorkoutSessionForUser(@PathVariable Long userId, @RequestBody WorkoutSession workoutSession) {
        return ResponseEntity.created(URI.create(String.format("/users/%s/workout_sessions", userId)))
                .body(userService.addWorkoutSessionForUser(userId, workoutSession));
    }
}
