# CinéTrack — Chapter 08 Snapshot

Snapshot of CinéTrack at the end of Chapter 8: *Common AI Slop and How to Spot It*.

## What changed in this chapter

No code changes. Chapter 8 is a review and pattern-recognition chapter that analyzes code from chapters 3–7. The application state is identical to the chapter-07 snapshot.

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
