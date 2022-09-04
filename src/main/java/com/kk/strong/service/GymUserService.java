package com.kk.strong.service;

import com.kk.strong.exception.UserNotFoundException;
import com.kk.strong.model.BodyReport;
import com.kk.strong.model.GymUser;
import com.kk.strong.model.WorkoutSession;
import com.kk.strong.model.dto.BodyReportDto;
import com.kk.strong.model.dto.GymUserDto;
import com.kk.strong.model.dto.WorkoutSessionDto;
import com.kk.strong.repository.GymUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class GymUserService {

    private final GymUserRepository gymUserRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<GymUserDto> getUsers() {
        log.info("Getting all users");
        return gymUserRepository
                .findAll()
                .stream()
                .map(user -> modelMapper.map(user, GymUserDto.class))
                .collect(Collectors.toList());
    }

    public GymUserDto getUser(Long userId) {
        log.info("Getting user with id: {}", userId);
        GymUser user = gymUserRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return modelMapper.map(user, GymUserDto.class);
    }

    public BodyReportDto addBodyReportForUser(Long userId, BodyReportDto bodyReportDto) {
        log.info("Adding body report for user with id: {}", userId);
        BodyReport bodyReport = modelMapper.map(bodyReportDto, BodyReport.class);
        GymUser user = gymUserRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        user.addBodyReport(bodyReport);
        return modelMapper.map(bodyReport, BodyReportDto.class);
    }

    public WorkoutSessionDto addWorkoutSessionForUser(Long userId, WorkoutSessionDto workoutSessionDto) {
        log.info("Adding workout session for user with id: {}", userId);
        WorkoutSession workoutSession = modelMapper.map(workoutSessionDto, WorkoutSession.class);
        GymUser user = gymUserRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        user.addWorkoutSession(workoutSession);
        return modelMapper.map(workoutSession, WorkoutSessionDto.class);
    }

    public GymUserDto updateUser(Long userId, GymUserDto gymUserDto) {
        log.info("Updating user with id: {}", userId);
        GymUser user = gymUserRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        modelMapper.map(gymUserDto, user);
        return modelMapper.map(user, GymUserDto.class);
    }

    public void deleteUser(Long userId) {
        log.info("Deleting user with id: {}", userId);
        gymUserRepository.deleteById(userId);
    }
}
