package com.kk.strong.service;

import com.kk.strong.exception.BodyReportNotFoundException;
import com.kk.strong.exception.WorkoutSessionNotFoundException;
import com.kk.strong.model.BodyReport;
import com.kk.strong.model.Exercise;
import com.kk.strong.model.WorkoutSession;
import com.kk.strong.repository.WorkoutSessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class WorkoutSessionService {

    private final WorkoutSessionRepository workoutSessionRepository;

    public List<WorkoutSession> getWorkoutSessions() {
        return workoutSessionRepository.findAll();
    }

    public WorkoutSession getWorkoutSession(Long workoutSessionId) {
        return workoutSessionRepository
                .findById(workoutSessionId)
                .orElseThrow(() -> new WorkoutSessionNotFoundException(workoutSessionId));
    }

    public void updateWorkoutSession(Long workoutSessionId, WorkoutSession workoutSession) {
        workoutSessionRepository.save(workoutSession);
    }

    public void deleteWorkoutSession(Long workoutSessionId) {
        workoutSessionRepository.deleteById(workoutSessionId);
    }

    public Exercise addExerciseForWorkoutSession(Long workoutSessionId, Exercise exercise) {
        workoutSessionRepository
                .findById(workoutSessionId)
                .orElseThrow(() -> new WorkoutSessionNotFoundException(workoutSessionId))
                .getExercises()
                .add(exercise);
        return exercise;
    }

    public List<Exercise> getExercisesForWorkoutSession(Long workoutSessionId) {
        return workoutSessionRepository
                .findById(workoutSessionId)
                .orElseThrow(() -> new WorkoutSessionNotFoundException(workoutSessionId))
                .getExercises();
    }
}
