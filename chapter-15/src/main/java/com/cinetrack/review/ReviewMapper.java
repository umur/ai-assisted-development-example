package com.cinetrack.review;

class ReviewMapper {

    static ReviewResponse toResponse(Review review) {
        return new ReviewResponse(
                review.getId(),
                review.getAuthor().getId(),
                review.getMovie().getId(),
                review.getRating(),
                review.getComment()
        );
    }
}
