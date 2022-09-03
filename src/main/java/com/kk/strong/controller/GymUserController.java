package com.kk.strong.controller;

import com.kk.strong.model.GymUser;
import com.kk.strong.service.GymUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('REGULAR_USER')")
@RequestMapping("/users")
public class GymUserController {

    private final GymUserService gymUserService;

    @GetMapping
    public ResponseEntity<List<GymUser>> getUsers() {
        return ResponseEntity.ok(gymUserService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GymUser> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(gymUserService.getUser(id));
    }
}
