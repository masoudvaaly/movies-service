package com.snapppay.movies.controller;

import com.snapppay.movies.dto.auth.AuthenticationResponseDto;
import com.snapppay.movies.dto.auth.LoginDto;
import com.snapppay.movies.dto.auth.RegisterDto;
import com.snapppay.movies.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin("*")
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponseDto> register(@RequestBody @Valid final RegisterDto registerDto) {
        return ResponseEntity.ok(userService.register(registerDto));
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponseDto> authenticate(@Valid @RequestBody final LoginDto loginDto) {
        return ResponseEntity.ok(userService.login(loginDto));
    }
}