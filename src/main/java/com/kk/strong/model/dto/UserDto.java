package com.kk.strong.model.dto;

import com.kk.strong.model.GenderType;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String name;
    private GenderType gender;
    private int age;
    private int height;
    private List<BodyReportDto> bodyReports;
    private List<WorkoutSessionDto> workoutSessions;
}
