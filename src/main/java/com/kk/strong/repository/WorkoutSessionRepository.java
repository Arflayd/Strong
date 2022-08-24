package com.kk.strong.repository;

import com.kk.strong.model.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {

}
