# CinéTrack — Chapter 12 Snapshot

Snapshot of CinéTrack at the end of Chapter 12: *CLAUDE.md and Persistent Project Context*.

## What changed in this chapter

- **`CLAUDE.md`** — persistent project context file capturing all CinéTrack conventions established in chapters 3–11; loaded automatically by Claude Code at the start of every session
- **`.claude/commands/new-feature.md`** — custom slash command that reads the watchlist archetype files and generates all seven feature files following CLAUDE.md conventions
- **`follow/`** — new package: `Follow` entity (self-referential AppUser relationship), repository with derived queries, service with idempotent follow and boolean unfollow, and CRUD controller; generated using `/new-feature Follow`

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
| GET | `/follows/users/{userId}/following` | List users this user follows |
| GET | `/follows/users/{userId}/followers` | List followers of this user |
| POST | `/follows` | Follow a user |
| DELETE | `/follows/users/{followerId}/following/{followingId}` | Unfollow a user |
