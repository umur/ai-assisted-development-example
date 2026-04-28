package com.cinetrack.movie;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.containers.PostgreSQLContainer;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
@Testcontainers
class MovieRepositoryTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16");


    @Autowired
    private MovieRepository movies;

    @Test
    void findByYearReturnsOnlyMatchingYear() {
        movies.save(new Movie("Heat", 1995, "Michael Mann"));
        movies.save(new Movie("Se7en", 1995, "David Fincher"));
        movies.save(new Movie("The Big Lebowski", 1998, "Joel Coen"));

        Page<Movie> page = movies.findByYear(1995, PageRequest.of(0, 10));

        assertThat(page.getTotalElements()).isEqualTo(2);
        assertThat(page.getContent())
                .extracting(Movie::getTitle)
                .containsExactlyInAnyOrder("Heat", "Se7en");
    }
}
