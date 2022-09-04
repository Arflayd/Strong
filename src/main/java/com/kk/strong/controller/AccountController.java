package com.kk.strong.controller;

import com.kk.strong.model.dto.GymUserDto;
import com.kk.strong.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<GymUserDto> registerAccount(
            @PathParam("username") String username,
            @PathParam("password") String password,
            @RequestBody GymUserDto gymUserDto) {
        return ResponseEntity
                .created(URI.create("/accounts"))
                .body(accountService.registerAccount(username, password, gymUserDto));
    }

}
