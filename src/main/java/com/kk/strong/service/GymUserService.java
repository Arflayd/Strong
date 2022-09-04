package com.kk.strong.service;

import com.kk.strong.exception.UserNotFoundException;
import com.kk.strong.model.*;
import com.kk.strong.model.dto.BodyReportDto;
import com.kk.strong.model.dto.ExerciseDto;
import com.kk.strong.model.dto.GymUserDto;
import com.kk.strong.model.dto.WorkoutSessionDto;
import com.kk.strong.repository.GymUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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

    public List<ExerciseDto> getLatestExercisesOfExerciseTypeForAllUsersWithSorting(ExerciseType exerciseType, RankingSortingType rankingSortingType) {
        List<ExerciseDto> exerciseDtos = new ArrayList<>();
        for (GymUser gymUser : gymUserRepository.findAll()) {
            List<WorkoutSession> latestWorkoutSessions = gymUser
                    .getWorkoutSessions()
                    .stream()
                    .sorted(Comparator.comparing(WorkoutSession::getTimestamp)).toList();
            for (WorkoutSession workoutSession : latestWorkoutSessions) {
                Optional<Exercise> latestExercise = workoutSession
                        .getExercises()
                        .stream()
                        .filter(exercise -> exercise
                                .getExerciseType()
                                .equals(exerciseType))
                        .findFirst();
                if (latestExercise.isPresent()) {
                    exerciseDtos.add(modelMapper.map(latestExercise, ExerciseDto.class));
                    break;
                }
            }
        }

        exerciseDtos.sort((firstExercise, secondExercise) -> switch (rankingSortingType) {
            case REPETITIONS -> Integer.compare(
                    firstExercise.getRepetitions(),
                    secondExercise.getRepetitions());
            case SETS -> Integer.compare(
                    firstExercise.getSets(),
                    secondExercise.getSets());
            case COMBINED -> Integer.compare(
                    firstExercise.getSets() * firstExercise.getRepetitions(),
                    secondExercise.getSets() * secondExercise.getRepetitions());
            case WEIGHT -> Integer.compare(
                    firstExercise.getLoadWeight(),
                    secondExercise.getLoadWeight());
        });

        return exerciseDtos;
    }
}
