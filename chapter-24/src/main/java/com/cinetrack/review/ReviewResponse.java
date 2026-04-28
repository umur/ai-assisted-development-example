package com.cinetrack.review;

import java.time.LocalDateTime;

public record ReviewResponse(Long id, Long authorId, Long movieId, Integer rating, String comment,
                             LocalDateTime createdAt) {
}
