package com.cinetrack.movie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
class MovieController {

    private final MovieRepository movies;

    MovieController(MovieRepository movies) {
        this.movies = movies;
    }

    @GetMapping
    Page<MovieResponse> list(Pageable pageable) {
        return movies.findAll(pageable).map(MovieMapper::toResponse);
    }

    @GetMapping("/{id}")
    ResponseEntity<MovieResponse> get(@PathVariable Long id) {
        return movies.findById(id)
                .map(MovieMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<MovieResponse> create(@RequestBody MovieRequest request) {
        Movie saved = movies.save(MovieMapper.toMovie(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(MovieMapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    ResponseEntity<MovieResponse> update(@PathVariable Long id, @RequestBody MovieRequest request) {
        return movies.findById(id).map(movie -> {
            movie.setTitle(request.title());
            movie.setYear(request.year());
            movie.setDirector(request.director());
            return ResponseEntity.ok(MovieMapper.toResponse(movies.save(movie)));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!movies.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        movies.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
