package com.cinetrack.follow;

class FollowMapper {

    static FollowResponse toResponse(Follow follow) {
        return new FollowResponse(
                follow.getId(),
                follow.getFollower().getId(),
                follow.getFollowing().getId()
        );
    }
}
