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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;

    private String name;
    private GenderType gender;
    private int age;
    private int height;

    @OneToMany
    private List<BodyReport> bodyReports = new ArrayList<>();
    @OneToMany
    private List<WorkoutSession> workoutSessions = new ArrayList<>();

    @ElementCollection
    @Enumerated(value = EnumType.STRING)
    private List<UserRole> roles = new ArrayList<>();

}