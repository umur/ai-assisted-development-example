# CinéTrack — Chapter 07 Snapshot

Snapshot of CinéTrack at the end of Chapter 7: *Reading AI Code Like It's Someone Else's PR*.

## What changed in this chapter

- **`watchlist/WatchlistService`** — new service layer extracted from `WatchlistController`; all write methods are `@Transactional`; methods return `Optional<WatchlistResponse>` so the controller maps to 404 cleanly
- **`watchlist/WatchlistController`** — refactored to hold only `WatchlistService`; no repository dependencies; no business logic; `removeMovie` now returns 404 when the movie is not in the watchlist (was silently returning 200)

## Run

```bash
docker compose up -d
mvn spring-boot:run
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
