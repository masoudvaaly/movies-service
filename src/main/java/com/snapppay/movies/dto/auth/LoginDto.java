package com.snapppay.movies.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank(message = "mobile.number.required")
        String mobileNumber,
        @NotBlank(message = "password.required")
        String password
) {
}