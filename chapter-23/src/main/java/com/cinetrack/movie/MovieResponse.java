package com.cinetrack.movie;

import java.time.LocalDateTime;

public record MovieResponse(Long id, String title, Integer year, String director,
                            int reviewCount, Double averageRating,
                            LocalDateTime createdAt) {
}
