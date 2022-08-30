package com.kk.strong.service;

import com.kk.strong.exception.BodyReportNotFoundException;
import com.kk.strong.exception.ExerciseNotFoundException;
import com.kk.strong.model.BodyReport;
import com.kk.strong.model.Exercise;
import com.kk.strong.model.dto.ExerciseDto;
import com.kk.strong.repository.ExerciseRepository;
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
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<ExerciseDto> getExercises() {
        log.info("Getting all exercise");
        return exerciseRepository
                .findAll()
                .stream()
                .map(exercise -> modelMapper.map(exercise, ExerciseDto.class))
                .collect(Collectors.toList());
    }

    public ExerciseDto getExercise(Long exerciseId) {
        log.info("Getting exercise with id: {}", exerciseId);
        Exercise exercise =  exerciseRepository
                .findById(exerciseId)
                .orElseThrow(() -> new ExerciseNotFoundException(exerciseId));
        return modelMapper.map(exercise, ExerciseDto.class);
    }

    public void updateExercise(Long exerciseId, ExerciseDto exerciseDto) {
        log.info("Updating exercise with id: {}", exerciseId);
        Exercise exercise = exerciseRepository
                .findById(exerciseId)
                .orElseThrow(() -> new ExerciseNotFoundException(exerciseId));
        modelMapper.map(exerciseDto, exercise);
    }

    public void deleteExercise(Long exerciseId){
        exerciseRepository.deleteById(exerciseId);
    }
}
