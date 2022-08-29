package com.kk.strong.service;

import com.kk.strong.exception.ExerciseNotFoundException;
import com.kk.strong.model.Exercise;
import com.kk.strong.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public List<Exercise> getExercises() {
        return exerciseRepository.findAll();
    }

    public Exercise getExercise(Long exerciseId) {
        return exerciseRepository
                .findById(exerciseId)
                .orElseThrow(() -> new ExerciseNotFoundException(exerciseId));
    }

    public void updateExercise(Long exerciseId, Exercise exercise) {
        exerciseRepository.save(exercise);
    }

    public void deleteExercise(Long exerciseId) {
        exerciseRepository.deleteById(exerciseId);
    }
}
