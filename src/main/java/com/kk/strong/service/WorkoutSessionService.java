package com.kk.strong.service;

import com.kk.strong.repository.WorkoutSessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class WorkoutSessionService {

    private final WorkoutSessionRepository workoutSessionRepository;


}
