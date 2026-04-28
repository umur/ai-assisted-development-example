# CinéTrack — Chapter 22 Snapshot

Snapshot of CinéTrack at the end of Chapter 22: *AI for SQL and Data: Query Design, Index Triage, Slow Hunts*.

## What changed in this chapter

No source code changes. All application code carried forward from Chapter 21.

Chapter 22 uses CinéTrack's schema as the subject for SQL and database performance examples: designing complex queries (top-reviewer leaderboard), identifying missing indexes using EXPLAIN ANALYZE, and applying keyset pagination to the review list endpoint. The examples describe the investigation and SQL, but the application code is unchanged.

## Run

```bash
docker compose up -d
mvn spring-boot:run
```

## Run tests

```bash
mvn test
```

## Useful SQL for investigation

```sql
-- Enable pg_stat_statements to track slow queries
CREATE EXTENSION IF NOT EXISTS pg_stat_statements;

-- Find slowest queries
SELECT query, total_exec_time, calls, mean_exec_time
FROM pg_stat_statements ORDER BY total_exec_time DESC LIMIT 20;

-- Check indexes on a table
SELECT indexname, indexdef FROM pg_indexes WHERE tablename = 'reviews';

-- EXPLAIN ANALYZE a slow query
EXPLAIN (ANALYZE, BUFFERS) SELECT * FROM reviews WHERE movie_id = 42;
```

## Claude Code workflow

```bash
# Start a development session
claude

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
