package com.kk.strong.service;

import com.kk.strong.exception.UserNotFoundException;
import com.kk.strong.model.*;
import com.kk.strong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.name())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public List<User> getUsers() {
        log.info("Getting all users");
        return userRepository.findAll();
    }

    public User getUser(Long userId) {
        log.info("Getting user with id: {}", userId);
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public User saveUser(User user) {
        log.info("Saving user: {}", user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User addRoleToUser(User user, UserRole userRole) {
        log.info("Adding role: {} to user with id: {}", userRole.name(), user.getId());
        user.getRoles().add(userRole);
        return userRepository.save(user);
    }

    public List<BodyReport> getBodyReportsForUser(Long userId) {
        log.info("Getting body reports for user with id: {}", userId);
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId))
                .getBodyReports();
    }

    public void saveBodyReportForUser(Long userId, BodyReport bodyReport) {
        log.info("Saving body report for user with id: {}", userId);
        userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId))
                .getBodyReports()
                .add(bodyReport);
    }

    public List<WorkoutSession> getWorkoutSessionsForUser(Long userId) {
        log.info("Getting workout sessions for user with id: {}", userId);
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId))
                .getWorkoutSessions();
    }

    public void saveWorkoutSessionForUser(Long userId, WorkoutSession workoutSession) {
        log.info("Saving workout session for user with id: {}", userId);
        userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId))
                .getWorkoutSessions()
                .add(workoutSession);
    }
}
