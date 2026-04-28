package com.cinetrack.review;

import com.cinetrack.movie.Movie;
import com.cinetrack.movie.MovieRepository;
import com.cinetrack.user.AppUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
                movies.findById(request.movieId()).map(movie -> {
                    Review review = reviews.save(new Review(author, movie, request.rating(), request.comment()));
                    int newCount = movie.getReviewCount() + 1;
                    double currentAverage = movie.getAverageRating() == null ? 0.0 : movie.getAverageRating();
                    movie.setReviewCount(newCount);
                    movie.setAverageRating((currentAverage * (newCount - 1) + request.rating()) / newCount);
                    movies.save(movie);
                    return ReviewMapper.toResponse(review);
                })
        );
    }

    @Transactional
    public boolean delete(Long id) {
        return reviews.findById(id).map(review -> {
            Movie movie = review.getMovie();
            reviews.delete(review);
            List<Review> remaining = reviews.findByMovieId(movie.getId());
            if (remaining.isEmpty()) {
                movie.setReviewCount(0);
                movie.setAverageRating(null);
            } else {
                movie.setReviewCount(remaining.size());
                movie.setAverageRating(
                        remaining.stream().mapToInt(Review::getRating).average().orElse(0.0)
                );
            }
            movies.save(movie);
            return true;
        }).orElse(false);
    }
}
