package com.kk.strong;

import com.kk.strong.model.User;
import com.kk.strong.model.UserRole;
import com.kk.strong.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class StrongApplication {

    public static void main(String[] args) {
        SpringApplication.run(StrongApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserService userService) {
        return args -> {
            userService.saveUser(new User(1L, "Adam", "adam", "adam", List.of(UserRole.REGULAR_USER)));
            userService.saveUser(new User(2L, "Eve", "eve", "eve", List.of(UserRole.REGULAR_USER, UserRole.PREMIUM_USER)));
        };
    }

}
