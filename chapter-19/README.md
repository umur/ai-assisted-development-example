# CinéTrack — Chapter 19 Snapshot

Snapshot of CinéTrack at the end of Chapter 19: *Debugging with AI: Stack Traces, Heisenbugs, Performance*.

## What changed in this chapter

No source code changes. All application code carried forward from Chapter 18.

Chapter 19 uses CinéTrack as the subject of debugging examples — reading a `LazyInitializationException` stack trace, tracing it to a missing `@Transactional` on a service method, identifying the N+1 query that the symptom fix would have introduced, and arriving at the correct fix using a `JOIN FETCH` query in the repository.

The examples are illustrative: they describe what a debugging session would look like against this codebase, not changes applied to it.

## Run

```bash
docker compose up -d
mvn spring-boot:run
```

## Run tests

```bash
mvn test
```

## Claude Code workflow

```bash
# Start a development session
claude

# Debug a LazyInitializationException: paste the trace with surrounding code and ask for diagnosis
# Enable query logging to detect N+1 queries
# Run /check-conventions before pushing
/check-conventions

# Prepare the PR
/pr-prep
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
