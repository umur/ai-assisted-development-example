# CinéTrack — Chapter 21 Snapshot

Snapshot of CinéTrack at the end of Chapter 21: *Migrations with AI: Library Upgrades, Java Bumps, Rewrites*.

## What changed in this chapter

No source code changes. All application code carried forward from Chapter 20.

Chapter 21 uses CinéTrack as the subject of a hypothetical migration scenario — imagining it was built on Spring Boot 2.7 / Java 17 / jjwt 0.11.x and tracing the incremental upgrade path to Spring Boot 4 / Java 21 / jjwt 0.12.x. The current codebase already reflects the end state; the chapter describes how to get there using AI-assisted incremental migration.

The examples are illustrative: they describe what the migration would look like, not changes applied to this snapshot.

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
# Before any migration: audit the scope
mvn dependency:tree
find src -name "*.java" -exec grep -l "import javax\." {} \; | wc -l

# During migration: compile after each step
mvn compile

# Check conventions before pushing
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
