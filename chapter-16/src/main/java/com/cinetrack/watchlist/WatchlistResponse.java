package com.cinetrack.watchlist;

import com.cinetrack.movie.MovieResponse;

import java.time.LocalDateTime;
import java.util.List;

public record WatchlistResponse(Long id, String name, Long ownerId, List<MovieResponse> movies,
                                LocalDateTime createdAt) {
}
