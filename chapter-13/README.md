# CinéTrack — Chapter 13 Snapshot

Snapshot of CinéTrack at the end of Chapter 13: *MCP: Connecting AI to Your Database, Logs, and APIs*.

## What changed in this chapter

- **`.claude/settings.json`** — MCP server configuration; registers the official postgres MCP server and the custom `cinetrack-stats` server
- **`mcp-servers/cinetrack-stats/`** — custom MCP server exposing two tools: `get_table_stats` (row counts for all tables) and `get_recent_reviews` (recent reviews with movie and author info); built with the MCP TypeScript SDK

No Java source changes. All application code carried forward from Chapter 12.

## Run

```bash
docker compose up -d
mvn spring-boot:run
```

## Run tests

```bash
mvn test
```

## MCP servers

Start the custom MCP server before launching Claude Code:

```bash
cd mcp-servers/cinetrack-stats
npm install
npm run build
```

Then start Claude Code from the project root. It will connect to both MCP servers automatically via `.claude/settings.json`.

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
