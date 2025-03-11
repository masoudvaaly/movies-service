package com.snapppay.movies.service;

import com.snapppay.movies.dto.AuthenticationResponseDto;
import com.snapppay.movies.dto.LoginDto;
import com.snapppay.movies.dto.RegisterDto;
import com.snapppay.movies.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public AuthenticationResponseDto register(@Valid RegisterDto registerDto) {
        return null;
    }

    public AuthenticationResponseDto login(@Valid LoginDto loginDto) {
        return null;
    }
}
