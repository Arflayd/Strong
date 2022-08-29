package com.kk.strong.service;

import com.kk.strong.exception.UserNotFoundException;
import com.kk.strong.model.*;
import com.kk.strong.model.dto.UserDto;
import com.kk.strong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BodyReportService bodyReportService;
    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.name())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public UserDto registerUser(String username, String password, UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.getRoles().add(UserRole.REGULAR_USER);
        userRepository.save(user);
        return userDto;
    }

    public List<UserDto> getUsers() {
        log.info("Getting all users");
        return userRepository
                .findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto getUser(Long userId) {
        log.info("Getting user with id: {}", userId);
        User user = userRepository
                        .findById(userId)
                        .orElseThrow(() -> new UserNotFoundException(userId));
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto addRoleToUser(Long userId, UserRole userRole) {
        log.info("Adding role: {} to user with id: {}", userRole.name(), userId);
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        user.getRoles().add(userRole);
        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    public List<BodyReport> getBodyReportsForUser(Long userId) {
        log.info("Getting body reports for user with id: {}", userId);
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId))
                .getBodyReports();
    }

    public BodyReport addBodyReportForUser(Long userId, BodyReport bodyReport) {
        log.info("Saving body report for user with id: {}", userId);
        //bodyReportService.createBodyReport(bodyReport);
        userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId))
                .getBodyReports()
                .add(bodyReport);
        return bodyReport;
    }

    public List<WorkoutSession> getWorkoutSessionsForUser(Long userId) {
        log.info("Getting workout sessions for user with id: {}", userId);
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId))
                .getWorkoutSessions();
    }

    public WorkoutSession addWorkoutSessionForUser(Long userId, WorkoutSession workoutSession) {
        log.info("Saving workout session for user with id: {}", userId);
        userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId))
                .getWorkoutSessions()
                .add(workoutSession);
        return workoutSession;
    }

    public UserDto updateUser(Long userId, UserDto userDto) {
        log.info("Updating user with id: {}", userId);
        User user = userRepository
                        .findById(userId)
                        .orElseThrow(() -> new UserNotFoundException(userId));
        modelMapper.map(userDto, user);
        userRepository.save(user);
        return userDto;
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
