package com.kk.strong.controller;

import com.kk.strong.model.User;
import com.kk.strong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/users")
    @PreAuthorize("hasAuthority('REGULAR_USER')")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping(path = "/user/{id}")
    @PreAuthorize("hasAuthority('REGULAR_USER')")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping(path = "/user")
    @PreAuthorize("hasAuthority('PREMIUM_USER')")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.created(URI.create("/user")).body(userService.saveUser(user));
    }

}
