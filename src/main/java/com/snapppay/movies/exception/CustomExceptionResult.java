package com.snapppay.movies.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CustomExceptionResult {

    private final Integer errorCode;
    private final String message;
    private final LocalDateTime timestamp;

    public CustomExceptionResult(CustomException exception) {
        this.errorCode = exception.getErrorCode();
        this.message = exception.getMessage();
        this.timestamp = LocalDateTime.now();
    }
}
