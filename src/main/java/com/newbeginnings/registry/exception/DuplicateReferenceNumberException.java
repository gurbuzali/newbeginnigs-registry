package com.newbeginnings.registry.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED, reason = "Duplicate reference number")
public class DuplicateReferenceNumberException extends RuntimeException {

    public DuplicateReferenceNumberException(String message) {
        super(message);
    }
}
