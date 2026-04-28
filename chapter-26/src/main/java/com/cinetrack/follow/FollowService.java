package com.cinetrack.follow;

import com.cinetrack.user.AppUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
class FollowService {

    private final FollowRepository follows;
    private final AppUserRepository users;

    FollowService(FollowRepository follows, AppUserRepository users) {
        this.follows = follows;
        this.users = users;
    }

    @Transactional(readOnly = true)
    public List<FollowResponse> findFollowing(Long userId) {
        return follows.findByFollowerId(userId).stream()
                .map(FollowMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<FollowResponse> findFollowers(Long userId) {
        return follows.findByFollowingId(userId).stream()
                .map(FollowMapper::toResponse)
                .toList();
    }

    @Transactional
    public Optional<FollowResponse> follow(FollowRequest request) {
        if (follows.existsByFollowerIdAndFollowingId(request.followerId(), request.followingId())) {
            return follows.findByFollowerId(request.followerId()).stream()
                    .filter(f -> f.getFollowing().getId().equals(request.followingId()))
                    .findFirst()
                    .map(FollowMapper::toResponse);
        }
        return users.findById(request.followerId()).flatMap(follower ->
                users.findById(request.followingId()).map(following ->
                        FollowMapper.toResponse(follows.save(new Follow(follower, following)))
                )
        );
    }

    @Transactional
    public boolean unfollow(Long followerId, Long followingId) {
        return follows.findByFollowerId(followerId).stream()
                .filter(f -> f.getFollowing().getId().equals(followingId))
                .findFirst()
                .map(f -> { follows.delete(f); return true; })
                .orElse(false);
    }
}
