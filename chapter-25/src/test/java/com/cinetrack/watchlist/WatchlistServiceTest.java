package com.cinetrack.watchlist;

import com.cinetrack.movie.Movie;
import com.cinetrack.movie.MovieRepository;
import com.cinetrack.user.AppUser;
import com.cinetrack.user.AppUserRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.containers.PostgreSQLContainer;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
@Testcontainers
class WatchlistServiceTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16");


    @Autowired WatchlistRepository watchlistRepository;
    @Autowired MovieRepository movieRepository;
    @Autowired AppUserRepository userRepository;

    WatchlistService service;
    AppUser owner;

    @BeforeEach
    void setUp() {
        service = new WatchlistService(watchlistRepository, userRepository, movieRepository);
        owner = userRepository.save(new AppUser("alice", "alice@example.com", "hash"));
    }

    @Test
    void list_returnsPaginatedWatchlists() {
        watchlistRepository.save(new Watchlist("Weekend", owner));
        watchlistRepository.save(new Watchlist("Classics", owner));

        Page<WatchlistResponse> result = service.list(Pageable.unpaged());

        assertEquals(2, result.getTotalElements());
    }

    @Test
    void findById_returnsEmpty_whenNotFound() {
        Optional<WatchlistResponse> result = service.findById(999L);
        assertTrue(result.isEmpty());
    }

    @Test
    void findById_returnsResponse_whenFound() {
        Watchlist watchlist = watchlistRepository.save(new Watchlist("Weekend", owner));

        Optional<WatchlistResponse> result = service.findById(watchlist.getId());

        assertTrue(result.isPresent());
        assertEquals("Weekend", result.get().name());
    }

    @Test
    void create_returnsEmpty_whenOwnerNotFound() {
        WatchlistRequest request = new WatchlistRequest("Weekend", 999L);

        Optional<WatchlistResponse> result = service.create(request);

        assertTrue(result.isEmpty());
    }

    @Test
    void create_createsWatchlist_whenOwnerExists() {
        WatchlistRequest request = new WatchlistRequest("Weekend", owner.getId());

        Optional<WatchlistResponse> result = service.create(request);

        assertTrue(result.isPresent());
        assertEquals("Weekend", result.get().name());
        assertEquals(owner.getId(), result.get().ownerId());
    }

    @Test
    void delete_returnsFalse_whenNotFound() {
        assertFalse(service.delete(999L));
    }

    @Test
    void delete_removesWatchlist_andReturnsTrue() {
        Watchlist watchlist = watchlistRepository.save(new Watchlist("Weekend", owner));

        assertTrue(service.delete(watchlist.getId()));
        assertTrue(watchlistRepository.findById(watchlist.getId()).isEmpty());
    }

    @Test
    void addMovie_returnsEmpty_whenWatchlistNotFound() {
        Movie movie = movieRepository.save(new Movie("Dune", 2021, "Villeneuve"));

        Optional<WatchlistResponse> result = service.addMovie(999L, movie.getId());

        assertTrue(result.isEmpty());
    }

    @Test
    void addMovie_returnsEmpty_whenMovieNotFound() {
        Watchlist watchlist = watchlistRepository.save(new Watchlist("Weekend", owner));

        Optional<WatchlistResponse> result = service.addMovie(watchlist.getId(), 999L);

        assertTrue(result.isEmpty());
    }

    @Test
    void addMovie_addsMovieToWatchlist() {
        Watchlist watchlist = watchlistRepository.save(new Watchlist("Weekend", owner));
        Movie movie = movieRepository.save(new Movie("Dune", 2021, "Villeneuve"));

        Optional<WatchlistResponse> result = service.addMovie(watchlist.getId(), movie.getId());

        assertTrue(result.isPresent());
        assertEquals(1, result.get().movies().size());
        assertEquals("Dune", result.get().movies().get(0).title());
    }

    @Test
    void removeMovie_returnsEmpty_whenWatchlistNotFound() {
        Optional<WatchlistResponse> result = service.removeMovie(999L, 42L);
        assertTrue(result.isEmpty());
    }

    @Test
    void removeMovie_returnsEmpty_whenMovieNotInWatchlist() {
        Watchlist watchlist = watchlistRepository.save(new Watchlist("Weekend", owner));
        Movie movie = movieRepository.save(new Movie("Dune", 2021, "Villeneuve"));

        // movie exists in DB but is not in the watchlist
        Optional<WatchlistResponse> result = service.removeMovie(watchlist.getId(), movie.getId());

        assertTrue(result.isEmpty());
    }

    @Test
    void removeMovie_removesMovie_andReturnsUpdatedWatchlist() {
        Movie movie = movieRepository.save(new Movie("Dune", 2021, "Villeneuve"));
        Watchlist watchlist = new Watchlist("Weekend", owner);
        watchlist.getMovies().add(movie);
        watchlistRepository.save(watchlist);

        Optional<WatchlistResponse> result = service.removeMovie(watchlist.getId(), movie.getId());

        assertTrue(result.isPresent());
        assertTrue(result.get().movies().isEmpty());
    }
}
