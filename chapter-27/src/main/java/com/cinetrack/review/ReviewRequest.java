package com.cinetrack.review;

public record ReviewRequest(Long authorId, Long movieId, Integer rating, String comment) {
}
