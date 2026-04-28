# CinéTrack — Chapter 16 Snapshot

Snapshot of CinéTrack at the end of Chapter 16: *Multi-File Refactors and Cross-Cutting Features*.

## What changed in this chapter

Added `createdAt` timestamp to Movie, Watchlist, and Review following the compile-test-commit pattern:

- **`Movie.java`** — added `LocalDateTime createdAt`, initialized in constructor, getter only
- **`MovieResponse.java`** — added `LocalDateTime createdAt` to the record
- **`MovieMapper.java`** — maps `movie.getCreatedAt()` in `toResponse()`
- **`Watchlist.java`** — same pattern as Movie
- **`WatchlistResponse.java`** — same pattern
- **`WatchlistMapper.java`** — same pattern
- **`Review.java`** — same pattern as Movie
- **`ReviewResponse.java`** — same pattern
- **`ReviewMapper.java`** — same pattern

## Run

```bash
docker compose up -d
mvn spring-boot:run
```

## Run tests

```bash
mvn test
```

## Endpoints

| Method | Path | Description |
|--------|------|-------------|
| POST | `/auth/login` | Get a JWT token |
| GET | `/movies` | List movies (includes reviewCount, averageRating, createdAt) |
| GET | `/movies/{id}` | Get movie by id |
| POST | `/movies` | Create movie |
| PUT | `/movies/{id}` | Update movie |
| DELETE | `/movies/{id}` | Delete movie |
| GET | `/watchlists` | List watchlists (includes createdAt) |
| GET | `/watchlists/{id}` | Get watchlist |
| POST | `/watchlists` | Create watchlist |
| DELETE | `/watchlists/{id}` | Delete watchlist |
| POST | `/watchlists/{id}/movies/{movieId}` | Add movie to watchlist |
| DELETE | `/watchlists/{id}/movies/{movieId}` | Remove movie from watchlist |
| GET | `/reviews` | List reviews (includes createdAt) |
| GET | `/reviews/{id}` | Get review by id |
| GET | `/reviews/movies/{movieId}` | List reviews for a movie |
| GET | `/reviews/users/{userId}` | List reviews by a user |
| POST | `/reviews` | Create review |
| DELETE | `/reviews/{id}` | Delete review |
| GET | `/follows/users/{userId}/following` | List users this user follows |
| GET | `/follows/users/{userId}/followers` | List followers of this user |
| POST | `/follows` | Follow a user |
| DELETE | `/follows/users/{followerId}/following/{followingId}` | Unfollow a user |
