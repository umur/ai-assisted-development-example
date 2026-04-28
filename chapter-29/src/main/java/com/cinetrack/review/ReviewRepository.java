package com.cinetrack.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findByMovieId(Long movieId, Pageable pageable);

    List<Review> findByMovieId(Long movieId);

    Page<Review> findByAuthorId(Long authorId, Pageable pageable);

    long countByAuthorId(Long authorId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.author.id = :authorId")
    Double findAverageRatingByAuthorId(@Param("authorId") Long authorId);
}
