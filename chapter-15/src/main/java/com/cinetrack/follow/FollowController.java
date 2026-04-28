package com.cinetrack.follow;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/follows")
class FollowController {

    private final FollowService service;

    FollowController(FollowService service) {
        this.service = service;
    }

    @GetMapping("/users/{userId}/following")
    List<FollowResponse> following(@PathVariable Long userId) {
        return service.findFollowing(userId);
    }

    @GetMapping("/users/{userId}/followers")
    List<FollowResponse> followers(@PathVariable Long userId) {
        return service.findFollowers(userId);
    }

    @PostMapping
    ResponseEntity<FollowResponse> follow(@RequestBody FollowRequest request) {
        return service.follow(request)
                .map(r -> ResponseEntity.status(HttpStatus.CREATED).body(r))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/users/{followerId}/following/{followingId}")
    ResponseEntity<Void> unfollow(@PathVariable Long followerId, @PathVariable Long followingId) {
        return service.unfollow(followerId, followingId)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
