package com.cinetrack.watchlist;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {

    Page<Watchlist> findByOwnerId(Long ownerId, Pageable pageable);
}
