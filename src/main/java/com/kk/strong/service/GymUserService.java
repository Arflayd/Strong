package com.kk.strong.service;

import com.kk.strong.model.GymUser;
import com.kk.strong.repository.GymUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class GymUserService {

    private final GymUserRepository gymUserRepository;

    public List<GymUser> getUsers() {
        log.info("Getting all users");
        return gymUserRepository.findAll();
    }

    public GymUser getUser(Long id) {
        log.info("Getting user with id: {}", id);
        return gymUserRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
