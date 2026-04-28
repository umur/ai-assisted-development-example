package com.cinetrack.watchlist;

import com.cinetrack.movie.MovieRepository;
import com.cinetrack.user.AppUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/watchlists")
class WatchlistController {

    private final WatchlistRepository watchlists;
    private final AppUserRepository users;
    private final MovieRepository movies;

    WatchlistController(WatchlistRepository watchlists, AppUserRepository users, MovieRepository movies) {
        this.watchlists = watchlists;
        this.users = users;
        this.movies = movies;
    }

    @GetMapping
    Page<WatchlistResponse> list(Pageable pageable) {
        return watchlists.findAll(pageable).map(WatchlistMapper::toResponse);
    }

    @GetMapping("/{id}")
    ResponseEntity<WatchlistResponse> get(@PathVariable Long id) {
        return watchlists.findById(id)
                .map(WatchlistMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<WatchlistResponse> create(@RequestBody WatchlistRequest request) {
        return users.findById(request.ownerId())
                .map(owner -> {
                    Watchlist saved = watchlists.save(new Watchlist(request.name(), owner));
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(WatchlistMapper.toResponse(saved));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!watchlists.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        watchlists.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/movies/{movieId}")
    ResponseEntity<WatchlistResponse> addMovie(@PathVariable Long id, @PathVariable Long movieId) {
        Watchlist watchlist = watchlists.findById(id).orElse(null);
        if (watchlist == null) return ResponseEntity.notFound().build();

        return movies.findById(movieId)
                .map(movie -> {
                    watchlist.getMovies().add(movie);
                    return ResponseEntity.ok(WatchlistMapper.toResponse(watchlists.save(watchlist)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/movies/{movieId}")
    ResponseEntity<WatchlistResponse> removeMovie(@PathVariable Long id, @PathVariable Long movieId) {
        Watchlist watchlist = watchlists.findById(id).orElse(null);
        if (watchlist == null) return ResponseEntity.notFound().build();

        watchlist.getMovies().removeIf(m -> m.getId().equals(movieId));
        return ResponseEntity.ok(WatchlistMapper.toResponse(watchlists.save(watchlist)));
    }
}
