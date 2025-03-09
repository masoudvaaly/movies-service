package com.snapppay.movies.service;

import com.snapppay.movies.domain.MovieEntity;
import com.snapppay.movies.repository.MovieRepository;
import com.snapppay.movies.service.omdb.OmdbFeign;
import com.snapppay.movies.service.omdb.dto.SearchResponse;
import com.snapppay.movies.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Set;

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
        Set<String> names = StringUtils.generateNames(3, 5, 100);
        names.add("aaa");
        names.add("bbb");
        names.parallelStream().forEach(name -> {
            SearchResponse searchResponse = omdbFeign.searchMovie(name, year);
            if (searchResponse != null && searchResponse.getItems() != null) {
                log.info("omdb search response {}", searchResponse);
                searchResponse.getItems().forEach(item -> {
                    try {
                        movieRepository.save(MovieEntity.builder()
                                .title(item.getTitle())
                                .imdbId(item.getImdbID())
                                .year(item.getYear())
                                .build());
                    } catch (Exception e) {
                        log.error("movie insert exception occurred", e);
                    }
                });

            }
        });

    }

}
