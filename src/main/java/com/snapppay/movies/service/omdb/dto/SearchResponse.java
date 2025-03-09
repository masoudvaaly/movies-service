package com.snapppay.movies.service.omdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SearchResponse {

    @JsonProperty("Search")
    private List<SearchItemDto> items;
    private String totalResults;
    private String Response;
}
