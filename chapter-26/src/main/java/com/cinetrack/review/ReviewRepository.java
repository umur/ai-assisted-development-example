package com.cinetrack.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findByMovieId(Long movieId, Pageable pageable);

    List<Review> findByMovieId(Long movieId);

    Page<Review> findByAuthorId(Long authorId, Pageable pageable);
}
