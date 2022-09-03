package com.kk.strong.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "body_reports")
public class BodyReport {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private GymUser gymUser;

    private LocalDateTime timestamp;
    private double weight;
    private double muscleWeight;
    private double bodyFat;
    private double waterLevel;
    private double proteinLevel;
}
