package com.kk.strong.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BodyReportNotFoundException extends ResponseStatusException {

    public BodyReportNotFoundException(Long bodyReportId) {
        super(HttpStatus.NOT_FOUND, String.format("Body report not found with id: %s", bodyReportId));
    }
}
