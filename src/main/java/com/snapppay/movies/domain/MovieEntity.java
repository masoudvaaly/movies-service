package com.snapppay.movies.domain;

import com.snapppay.movies.enums.Rating;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movies")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class MovieEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_generator")
    @SequenceGenerator(name = "movie_generator", sequenceName = "movie_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "year")
    private String year;

    @Column(name = "genre")
    private String genre;

    @Column(name = "director")
    private String director;

    @Enumerated(EnumType.STRING)
    @Column(name = "rating")
    Rating rating;

    @Column(name = "rate")
    private float rate;
}