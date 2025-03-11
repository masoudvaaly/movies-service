package com.snapppay.movies.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterDto(
        @NotBlank(message = "mobile.number.required")
        String mobileNumber,
        @NotBlank(message = "password.required")
        String password,
        String name,
        String surname
) {
}