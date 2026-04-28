# CinéTrack — Chapter 02 Snapshot

Starter Spring Boot 4 + Postgres project. This is the state at the end of chapter 2 (*Your AI Workbench*) — the reader has installed Copilot and Claude Code, scaffolded this project, and run it once.

## Run

```bash
docker compose up -d
mvn spring-boot:run
```

Then `curl http://localhost:8080/actuator/health` (no actuator endpoint yet — chapter 2 ends without one; the smoke test is just that the app starts).

## What changed in this chapter

This is the first code-bearing chapter, so everything here is new:

- `pom.xml` — Spring Boot 4 + JPA + validation + Postgres + test
- `src/main/java/com/cinetrack/CineTrackApplication.java` — empty `@SpringBootApplication`
- `src/main/resources/application.yml` — Postgres datasource + JPA config
- `docker-compose.yml` — Postgres 16 service for local dev

No domain entities yet. Those start in chapter 3.
