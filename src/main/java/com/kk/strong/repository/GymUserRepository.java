package com.kk.strong.repository;

import com.kk.strong.model.GymUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymUserRepository extends JpaRepository<GymUser, Long> {
}
