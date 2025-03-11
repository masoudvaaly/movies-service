package com.snapppay.movies.dto.movie;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserMoviePreferencesDto {
    private List<String> actors;
    private List<String> genres;
    private List<String> countries;
    //todo we can add any other values suited for filtering movies
}
