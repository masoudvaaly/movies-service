package com.snapppay.movies.controller;

import com.snapppay.movies.dto.movie.UserMoviePreferencesDto;
import com.snapppay.movies.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
@CrossOrigin("*")
@Validated
public class MovieController {

    private final MovieService movieService;

    @PostMapping("preferences")
    public ResponseEntity<Void> addUserPreferences(@RequestBody @Valid final UserMoviePreferencesDto dto) {
        // todo response could be a dto based on ui needs
        movieService.addUserPreferences(dto);
        return ResponseEntity.noContent().build();
    }
}