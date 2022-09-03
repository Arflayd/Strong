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
@Table(name = "accounts")
public class Account {

    @Id
    private String username;
    private String password;

    @ElementCollection
    @Enumerated(value = EnumType.STRING)
    private List<AccountRole> roles = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private GymUser gymUser;
}
