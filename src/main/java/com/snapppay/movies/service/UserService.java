package com.snapppay.movies.service;

import com.snapppay.movies.domain.UserEntity;
import com.snapppay.movies.dto.AuthenticationResponseDto;
import com.snapppay.movies.dto.LoginDto;
import com.snapppay.movies.dto.RegisterDto;
import com.snapppay.movies.exception.CustomException;
import com.snapppay.movies.mapper.UserMapper;
import com.snapppay.movies.repository.UserRepository;
import com.snapppay.movies.utils.JwtUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtil;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthenticationResponseDto register(@Valid RegisterDto registerDto) {
        log.info("register user started {}", registerDto);
        userRepository.findByMobileNumber(registerDto.mobileNumber()).ifPresent(
                user -> {
                    throw new CustomException("user.already.exist", 400);
                }
        );
        UserEntity user = userMapper.toUser(registerDto);
        user.setPassword(passwordEncoder.encode(registerDto.password()));

        userRepository.save(user);
        return new AuthenticationResponseDto(jwtUtil.generateToken(registerDto.mobileNumber()));
    }

    public AuthenticationResponseDto login(@Valid LoginDto loginDto) {
        log.info("login for user {}", loginDto);
        UserEntity user = userRepository.findByMobileNumber(loginDto.mobileNumber())
                .orElseThrow(() -> new CustomException("user.not.found", 401));

        boolean passwordMatched = passwordEncoder.matches(loginDto.password(), user.getPassword());
        if (!passwordMatched) {
            log.error("incorrect password for user {}", loginDto.mobileNumber());
            throw new CustomException("password.incorrect", 401);
        }

        return new AuthenticationResponseDto(jwtUtil.generateToken(loginDto.mobileNumber()));
    }
}