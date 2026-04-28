package com.cinetrack.review;

import com.cinetrack.movie.MovieRepository;
import com.cinetrack.user.AppUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
class ReviewService {

    private final ReviewRepository reviews;
    private final AppUserRepository users;
    private final MovieRepository movies;

    ReviewService(ReviewRepository reviews, AppUserRepository users, MovieRepository movies) {
        this.reviews = reviews;
        this.users = users;
        this.movies = movies;
    }

    @Transactional(readOnly = true)
    public Page<ReviewResponse> list(Pageable pageable) {
        return reviews.findAll(pageable).map(ReviewMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Optional<ReviewResponse> findById(Long id) {
        return reviews.findById(id).map(ReviewMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<ReviewResponse> findByMovie(Long movieId, Pageable pageable) {
        return reviews.findByMovieId(movieId, pageable).map(ReviewMapper::toResponse);
    }

    @Transactional
    public Optional<ReviewResponse> create(ReviewRequest request) {
        return users.findById(request.authorId()).flatMap(author ->
                movies.findById(request.movieId()).map(movie ->
                        ReviewMapper.toResponse(reviews.save(
                                new Review(author, movie, request.rating(), request.comment())))
                )
        );
    }

    @Transactional
    public boolean delete(Long id) {
        if (!reviews.existsById(id)) {
            return false;
        }
        reviews.deleteById(id);
        return true;
    }
}
