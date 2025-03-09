package com.snapppay.movies.service;

import com.snapppay.movies.repository.MovieRepository;
import com.snapppay.movies.service.omdb.OmdbFeign;
import com.snapppay.movies.service.omdb.dto.SearchResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final OmdbFeign omdbFeign;

    @Scheduled(cron = "0 * * * * ?")
    public void fetchMovies() {

        //todo for test we use current year, upgrade it to search on all years
        String year = "2025";
        List<String> names = List.of("aaa", "bbb", "ccc", "ddd", "eee", "fff", "ggg");
        names.forEach(name -> {
            SearchResponse searchResponse = omdbFeign.searchMovie(name, year);
            if (searchResponse != null && searchResponse.getItems() != null) {

            }
            log.info("omdb search response {}", searchResponse);
        });

    }

}
