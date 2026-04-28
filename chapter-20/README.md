# CinéTrack — Chapter 20 Snapshot

Snapshot of CinéTrack at the end of Chapter 20: *Code Archaeology: Onboarding to a Strange Codebase*.

## What changed in this chapter

No source code changes. All application code carried forward from Chapter 19.

Chapter 20 uses CinéTrack as the subject codebase for an archaeological exploration — reading entry points, mapping the domain model, understanding the implementation pattern from a single feature, reading git history for context, and planning the addition of a `genre` field without yet implementing it.

The examples are illustrative: they describe what onboarding to this codebase would look like, not changes applied to it.

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

# Explore the codebase: read controllers, then entities, then one full feature
# Ask for architecture map, domain model description, and invariants
# Check git history for context on unusual patterns
git log --oneline --graph -50
git blame src/main/java/com/cinetrack/movie/Movie.java

# Audit conventions before pushing
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
