# CinéTrack — Chapter 14 Snapshot

Snapshot of CinéTrack at the end of Chapter 14: *Plan Mode: Designing Before Doing*.

## What changed in this chapter

- **`Movie.java`** — added `reviewCount` (int, default 0) and `averageRating` (Double, nullable) fields with getters and setters
- **`MovieResponse.java`** — added `reviewCount` and `averageRating` to the record
- **`MovieMapper.java`** — updated `toResponse()` to map the two new fields
- **`ReviewRepository.java`** — added `List<Review> findByMovieId(Long movieId)` for post-delete recalculation
- **`ReviewService.java`** — updated `create()` to recalculate running average after saving a review; updated `delete()` to recompute count and average from remaining reviews; sets `averageRating` to null when the last review is deleted

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
| GET | `/movies` | List movies (paginated, includes reviewCount and averageRating) |
| GET | `/movies/{id}` | Get movie by id |
| POST | `/movies` | Create movie |
| PUT | `/movies/{id}` | Update movie |
| DELETE | `/movies/{id}` | Delete movie |
| GET | `/watchlists` | List watchlists |
| GET | `/watchlists/{id}` | Get watchlist |
| POST | `/watchlists` | Create watchlist |
| DELETE | `/watchlists/{id}` | Delete watchlist |
| POST | `/watchlists/{id}/movies/{movieId}` | Add movie to watchlist |
| DELETE | `/watchlists/{id}/movies/{movieId}` | Remove movie from watchlist |
| GET | `/reviews` | List reviews (paginated) |
| GET | `/reviews/{id}` | Get review by id |
| GET | `/reviews/movies/{movieId}` | List reviews for a movie |
| POST | `/reviews` | Create review |
| DELETE | `/reviews/{id}` | Delete review |
| GET | `/follows/users/{userId}/following` | List users this user follows |
| GET | `/follows/users/{userId}/followers` | List followers of this user |
| POST | `/follows` | Follow a user |
| DELETE | `/follows/users/{followerId}/following/{followingId}` | Unfollow a user |
