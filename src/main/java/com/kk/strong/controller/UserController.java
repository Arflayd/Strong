package com.kk.strong.controller;

import com.kk.strong.model.BodyReport;
import com.kk.strong.model.Exercise;
import com.kk.strong.model.User;
import com.kk.strong.model.WorkoutSession;
import com.kk.strong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('REGULAR_USER')")
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping(path = "/users")
    @PreAuthorize("hasAuthority('PREMIUM_USER')")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.created(URI.create("/users")).body(userService.saveUser(user));
    }

    @GetMapping(path = "/users/{userId}/body_reports/")
    public ResponseEntity<List<BodyReport>> getBodyReportsForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getBodyReportsForUser(userId));
    }

    @GetMapping(path = "/users/{userId}/workout_sessions")
    public ResponseEntity<List<WorkoutSession>> getWorkoutSessionsForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getWorkoutSessionsForUser(userId));
    }
}
