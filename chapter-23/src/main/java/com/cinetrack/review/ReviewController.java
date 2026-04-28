package com.cinetrack.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
class ReviewController {

    private final ReviewService service;

    ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping
    Page<ReviewResponse> list(Pageable pageable) {
        return service.list(pageable);
    }

    @GetMapping("/{id}")
    ResponseEntity<ReviewResponse> get(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/movies/{movieId}")
    Page<ReviewResponse> byMovie(@PathVariable Long movieId, Pageable pageable) {
        return service.findByMovie(movieId, pageable);
    }

    @GetMapping("/users/{userId}")
    ResponseEntity<Page<ReviewResponse>> byAuthor(@PathVariable Long userId, Pageable pageable) {
        return service.findByAuthor(userId, pageable)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<ReviewResponse> create(@RequestBody ReviewRequest request) {
        return service.create(request)
                .map(r -> ResponseEntity.status(HttpStatus.CREATED).body(r))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
