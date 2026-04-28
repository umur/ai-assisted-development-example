package com.cinetrack.watchlist;

import com.cinetrack.movie.MovieMapper;
import com.cinetrack.movie.MovieResponse;

import java.util.List;

class WatchlistMapper {

    static WatchlistResponse toResponse(Watchlist watchlist) {
        List<MovieResponse> movies = watchlist.getMovies().stream()
                .map(MovieMapper::toResponse)
                .toList();
        return new WatchlistResponse(
                watchlist.getId(),
                watchlist.getName(),
                watchlist.getOwner().getId(),
                movies
        );
    }
}
