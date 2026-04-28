package com.cinetrack.movie;

public class MovieMapper {

    public static MovieResponse toResponse(Movie movie) {
        return new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getYear(),
                movie.getDirector()
        );
    }

    public static Movie toMovie(MovieRequest request) {
        return new Movie(request.title(), request.year(), request.director());
    }
}
