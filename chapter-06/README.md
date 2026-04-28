# CinéTrack — Chapter 06 Snapshot

Snapshot of CinéTrack at the end of Chapter 6: *Prompting Patterns That Actually Work*.

## What changed in this chapter

- **`watchlist/`** — new package: `Watchlist` entity with `@ManyToOne` owner and `@ManyToMany` movies, plus repository, request/response records, mapper, and CRUD controller
- `Watchlist` demonstrates all five prompting patterns from the chapter: spec prompts, example prompts, anti-examples, "show me three options", and structured output requests

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
| PUT | `/watchlists/{id}` | Update watchlist |
| DELETE | `/watchlists/{id}` | Delete watchlist |
| POST | `/watchlists/{id}/movies/{movieId}` | Add movie to watchlist |
| DELETE | `/watchlists/{id}/movies/{movieId}` | Remove movie from watchlist |
