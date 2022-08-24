package com.kk.strong.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {

    public UserNotFoundException(Long userId){
        super(HttpStatus.NOT_FOUND, String.format("User not found with id: %s", userId));
    }
}
