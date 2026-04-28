package com.cinetrack.movie;

public record MovieResponse(Long id, String title, Integer year, String director,
                            int reviewCount, Double averageRating) {
}
