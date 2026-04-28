package com.cinetrack.watchlist;

import com.cinetrack.movie.MovieRepository;
import com.cinetrack.user.AppUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
class WatchlistService {

    private final WatchlistRepository watchlists;
    private final AppUserRepository users;
    private final MovieRepository movies;

    WatchlistService(WatchlistRepository watchlists, AppUserRepository users, MovieRepository movies) {
        this.watchlists = watchlists;
        this.users = users;
        this.movies = movies;
    }

    @Transactional(readOnly = true)
    public Page<WatchlistResponse> list(Pageable pageable) {
        return watchlists.findAll(pageable).map(WatchlistMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Optional<WatchlistResponse> findById(Long id) {
        return watchlists.findById(id).map(WatchlistMapper::toResponse);
    }

    @Transactional
    public Optional<WatchlistResponse> create(WatchlistRequest request) {
        return users.findById(request.ownerId())
                .map(owner -> WatchlistMapper.toResponse(watchlists.save(new Watchlist(request.name(), owner))));
    }

    @Transactional
    public boolean delete(Long id) {
        if (!watchlists.existsById(id)) {
            return false;
        }
        watchlists.deleteById(id);
        return true;
    }

    @Transactional
    public Optional<WatchlistResponse> addMovie(Long id, Long movieId) {
        return watchlists.findById(id).flatMap(watchlist ->
                movies.findById(movieId).map(movie -> {
                    watchlist.getMovies().add(movie);
                    return WatchlistMapper.toResponse(watchlists.save(watchlist));
                })
        );
    }

    @Transactional
    public Optional<WatchlistResponse> removeMovie(Long id, Long movieId) {
        return watchlists.findById(id).flatMap(watchlist -> {
            boolean removed = watchlist.getMovies().removeIf(m -> m.getId().equals(movieId));
            if (!removed) {
                return Optional.empty();
            }
            return Optional.of(WatchlistMapper.toResponse(watchlists.save(watchlist)));
        });
    }
}
