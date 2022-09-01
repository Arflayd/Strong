package com.kk.strong.service;

import com.kk.strong.exception.BodyReportNotFoundException;
import com.kk.strong.exception.UserNotFoundException;
import com.kk.strong.exception.WorkoutSessionNotFoundException;
import com.kk.strong.model.BodyReport;
import com.kk.strong.model.User;
import com.kk.strong.model.UserRole;
import com.kk.strong.model.WorkoutSession;
import com.kk.strong.model.dto.BodyReportDto;
import com.kk.strong.model.dto.UserDto;
import com.kk.strong.model.dto.WorkoutSessionDto;
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
    private final WorkoutSessionService workoutSessionService;
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
        return modelMapper.map(user, UserDto.class);
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

    public void addRoleForUser(Long userId, UserRole userRole) {
        log.info("Adding role: {} to user with id: {}", userRole.name(), userId);
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        user.getRoles().add(userRole);
    }

    public BodyReportDto addBodyReportForUser(Long userId, BodyReportDto bodyReportDto) {
        log.info("Adding body report for user with id: {}", userId);
        BodyReport bodyReport = modelMapper.map(bodyReportDto, BodyReport.class);
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        user.addBodyReport(bodyReport);
        return modelMapper.map(bodyReport, BodyReportDto.class);
    }

    public WorkoutSessionDto addWorkoutSessionForUser(Long userId, WorkoutSessionDto workoutSessionDto) {
        log.info("Adding workout session for user with id: {}", userId);
        WorkoutSession workoutSession = modelMapper.map(workoutSessionDto, WorkoutSession.class);
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        user.addWorkoutSession(workoutSession);
        return modelMapper.map(workoutSession, WorkoutSessionDto.class);
    }

    public UserDto updateUser(Long userId, UserDto userDto) {
        log.info("Updating user with id: {}", userId);
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        modelMapper.map(userDto, user);
        return modelMapper.map(user, UserDto.class);
    }

    public void deleteUser(Long userId) {
        log.info("Deleting user with id: {}", userId);
        userRepository.deleteById(userId);
    }
}
