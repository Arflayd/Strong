package com.kk.strong.controller;

import com.kk.strong.service.WorkoutSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('REGULAR_USER')")
public class WorkoutSessionController {

    private final WorkoutSessionService workoutSessionService;


}
