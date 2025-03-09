package com.snapppay.movies.service.omdb;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OmdbFeignConfig {

    @Value("${omdb.api-key}")
    private String apikey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.query("apikey", apikey);
        };
    }
}
