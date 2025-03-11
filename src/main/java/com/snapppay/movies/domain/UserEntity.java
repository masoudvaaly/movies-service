package com.snapppay.movies.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Setter
@Getter
public class UserEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_seq", allocationSize = 1)
    @Column(updatable = false, nullable = false)
    private long id;

    @Column(nullable = false, unique = true)
    private String mobileNumber;

    private String password;

    private String name;

    private String surname;
}