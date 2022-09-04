package com.kk.strong.repository;

import com.kk.strong.model.Exercise;
import com.kk.strong.model.ExerciseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findAllByWorkoutSession_GymUser_IdAndExerciseTypeOrderByWorkoutSession_Timestamp(Long gymUserId, ExerciseType exerciseType);
}
