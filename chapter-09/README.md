# CinéTrack — Chapter 09 Snapshot

Snapshot of CinéTrack at the end of Chapter 9: *Tests as Your Safety Net for AI-Generated Code*.

## What changed in this chapter

- **`pom.xml`** — added H2 test dependency for `@DataJpaTest` slices
- **`WatchlistServiceTest`** — twelve behavioral tests covering the full `WatchlistService` contract: all failure paths, boundary conditions for collection operations, and the regression guard for the silent-200 bug fixed in chapter 7

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
