package com.cinetrack.stats;

public record UserStatsResponse(
        Long userId,
        String username,
        long reviewCount,
        Double averageRatingGiven,
        long followingCount,
        long followersCount
) {}
