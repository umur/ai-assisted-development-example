package com.cinetrack.stats;

import com.cinetrack.follow.FollowRepository;
import com.cinetrack.review.ReviewRepository;
import com.cinetrack.user.AppUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
class UserStatsService {

    private final AppUserRepository users;
    private final ReviewRepository reviews;
    private final FollowRepository follows;

    UserStatsService(AppUserRepository users, ReviewRepository reviews, FollowRepository follows) {
        this.users = users;
        this.reviews = reviews;
        this.follows = follows;
    }

    @Transactional(readOnly = true)
    public Optional<UserStatsResponse> findByUserId(Long userId) {
        return users.findById(userId).map(user -> new UserStatsResponse(
                user.getId(),
                user.getUsername(),
                reviews.countByAuthorId(userId),
                reviews.findAverageRatingByAuthorId(userId),
                follows.countByFollowerId(userId),
                follows.countByFollowingId(userId)
        ));
    }
}
