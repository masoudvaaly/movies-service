package com.snapppay.movies.exception;

import jakarta.security.auth.message.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomExceptionResult> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Locale locale = getLocale();
        String error = StringUtils.join(ex.getBindingResult().getFieldErrors().stream()
                        .map(er ->
                                messageSource.getMessage(Objects.requireNonNull(er.getDefaultMessage()), null,
                                        "validation error", locale)).toList(),
                ",");

        return new ResponseEntity<>(new CustomExceptionResult(HttpStatus.BAD_REQUEST.value(), error, LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<CustomExceptionResult> handleAuthException(AuthException exception) {
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    private Locale getLocale() {
        return new Locale("fa");
    }
}