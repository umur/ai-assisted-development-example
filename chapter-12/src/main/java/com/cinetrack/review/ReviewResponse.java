package com.cinetrack.review;

public record ReviewResponse(Long id, Long authorId, Long movieId, Integer rating, String comment) {
}
