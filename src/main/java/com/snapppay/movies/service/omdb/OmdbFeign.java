package com.snapppay.movies.service.omdb;

import com.snapppay.movies.service.omdb.dto.SearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "omdbFeign", url = "${omdb.base-url}", configuration = OmdbFeignConfig.class)
public interface OmdbFeign {

    @GetMapping
    SearchResponse searchMovie(@RequestParam("s") String title,
                               @RequestParam("y") String year);
}
