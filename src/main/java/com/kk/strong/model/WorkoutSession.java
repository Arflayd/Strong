package com.kk.strong.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "workout_sessions")
public class WorkoutSession {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime timestamp;

    @Enumerated(value = EnumType.STRING)
    private WorkoutCategory workoutCategory;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Exercise> exercises = new ArrayList<>();

}
