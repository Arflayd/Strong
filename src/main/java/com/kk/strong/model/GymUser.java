package com.kk.strong.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gym_users")
public class GymUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Account account;

    private String name;
    private GenderType gender;
    private int age;
    private int height;

    @OneToMany(
            mappedBy = "gymUser",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<BodyReport> bodyReports = new ArrayList<>();
    @OneToMany(
            mappedBy = "gymUser",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<WorkoutSession> workoutSessions = new ArrayList<>();
}
