package com.cinetrack.movie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MovieRepositoryTest {

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
