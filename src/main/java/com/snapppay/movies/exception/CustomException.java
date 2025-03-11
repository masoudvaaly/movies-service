package com.snapppay.movies.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {
    private int errorCode;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}