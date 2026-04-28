package com.cinetrack.stats;

import com.cinetrack.follow.Follow;
import com.cinetrack.follow.FollowRepository;
import com.cinetrack.movie.Movie;
import com.cinetrack.movie.MovieRepository;
import com.cinetrack.review.Review;
import com.cinetrack.review.ReviewRepository;
import com.cinetrack.user.AppUser;
import com.cinetrack.user.AppUserRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
class UserStatsServiceTest {

    @Autowired UserStatsService service;
    @Autowired AppUserRepository users;
    @Autowired ReviewRepository reviews;
    @Autowired FollowRepository follows;
    @Autowired MovieRepository movies;

    @Test
    void returns_correct_stats_for_active_user() {
        AppUser alice = users.save(new AppUser("alice", "alice@test.com", "hash"));
        AppUser bob = users.save(new AppUser("bob", "bob@test.com", "hash"));
        AppUser carol = users.save(new AppUser("carol", "carol@test.com", "hash"));

        Movie m1 = movies.save(new Movie("Film A", 2020, "Director"));
        Movie m2 = movies.save(new Movie("Film B", 2021, "Director"));
        reviews.save(new Review(alice, m1, 4, "good"));
        reviews.save(new Review(alice, m2, 2, "meh"));
        follows.save(new Follow(alice, bob));
        follows.save(new Follow(carol, alice));

        Optional<UserStatsResponse> result = service.findByUserId(alice.getId());

        assertThat(result).isPresent();
        UserStatsResponse stats = result.get();
        assertThat(stats.username()).isEqualTo("alice");
        assertThat(stats.reviewCount()).isEqualTo(2);
        assertThat(stats.averageRatingGiven()).isCloseTo(3.0, within(0.001));
        assertThat(stats.followingCount()).isEqualTo(1);
        assertThat(stats.followersCount()).isEqualTo(1);
    }

    @Test
    void returns_zero_stats_for_user_with_no_activity() {
        AppUser dave = users.save(new AppUser("dave", "dave@test.com", "hash"));

        Optional<UserStatsResponse> result = service.findByUserId(dave.getId());

        assertThat(result).isPresent();
        assertThat(result.get().reviewCount()).isEqualTo(0);
        assertThat(result.get().averageRatingGiven()).isNull();
        assertThat(result.get().followingCount()).isEqualTo(0);
        assertThat(result.get().followersCount()).isEqualTo(0);
    }

    @Test
    void returns_empty_for_unknown_user() {
        assertThat(service.findByUserId(999L)).isEmpty();
    }
}
