# CinéTrack — Chapter 18 Snapshot

Snapshot of CinéTrack at the end of Chapter 18: *Custom Slash Commands and Skills*.

## What changed in this chapter

No Java source changes. All application code carried forward from Chapter 17.

- **`.claude/commands/add-test.md`** — new `/add-test` command that generates `@DataJpaTest` integration tests for a given service class, following the `WatchlistServiceTest` archetype
- **`.claude/commands/check-conventions.md`** — new `/check-conventions` command that audits all Java files in the current git diff against CinéTrack's entity, service, controller, and code style conventions
- **`.claude/commands/add-endpoint.md`** — new `/add-endpoint` command that adds a single new endpoint to an existing controller and service

## Command library

| Command | Purpose |
|---------|---------|
| `/new-feature` | Generate all seven files for a new feature |
| `/add-test` | Generate integration tests for a service |
| `/add-endpoint` | Add a single new endpoint to an existing feature |
| `/check-conventions` | Audit the current diff against CLAUDE.md |
| `/pr-prep` | Run tests and prepare PR description |

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
# Start a development session — hooks activate automatically
claude

# Generate tests for a service
/add-test ReviewService

# Add a new endpoint
/add-endpoint GET /movies/:id/similar - return 5 movies with the same director

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
