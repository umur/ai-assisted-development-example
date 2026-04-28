# CinéTrack — Chapter 17 Snapshot

Snapshot of CinéTrack at the end of Chapter 17: *Hooks, Subagents, and Custom Workflows*.

## What changed in this chapter

No Java source changes. All application code carried forward from Chapter 16.

- **`.claude/settings.json`** — added `PostToolUse` hook that compiles Java files after every write/edit (exit code 2 on failure sends error to model); added `Stop` hook that runs the full test suite when a Claude Code session ends
- **`.claude/commands/pr-prep.md`** — new `/pr-prep` command that runs tests in the main session while a subagent audits the diff against CLAUDE.md conventions and drafts a PR title and description

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

# Before pushing, run PR prep
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
