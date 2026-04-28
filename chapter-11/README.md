# CinéTrack — Chapter 11 Snapshot

Snapshot of CinéTrack at the end of Chapter 11: *Feeding the AI: Files, Symbols, and Conventions*.

## What changed in this chapter

- **`review/`** — new package with `Review` entity, `ReviewRepository`, `ReviewRequest`, `ReviewResponse`, `ReviewMapper`, `ReviewService`, and `ReviewController`
- Generated using deliberate context selection: `AppUser.java`, `Movie.java`, `Watchlist.java`, and `WatchlistService.java` as context — no extra instructions required to produce code that fits CinéTrack conventions

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
| GET | `/movies` | List movies (paginated) |
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
