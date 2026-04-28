# CinéTrack — AI-Assisted Development with Spring Boot

Companion code for the book **AI-Assisted Development with Spring Boot: From Copilot Autocomplete to Claude Code Agents** by Umur Inan.

## Structure

Each directory is a self-contained snapshot of CinéTrack at the end of that chapter. You can open any chapter in isolation and run it independently.

| Chapter | What's added |
|---------|-------------|
| `chapter-02` | Starter project — Spring Boot app boots, connects to PostgreSQL |
| `chapter-03` | `Movie` entity, `MovieRepository`, first `@DataJpaTest` |
| `chapter-04` | `MovieController`, DTOs, `MovieMapper` |
| `chapter-05` | `AppUser`, JWT authentication, `/auth/login` endpoint |
| `chapter-06` | `Watchlist` CRUD — entity, service, controller |
| `chapter-07` | Code review pass — refactored service/controller patterns |
| `chapter-08` | Slop case studies — before/after examples of AI mistakes |
| `chapter-09` | Full test suite with `@DataJpaTest` |
| `chapter-10` | Context failure walkthrough — `Review` built with insufficient context |
| `chapter-11` | `Review` entity and service — rebuilt with proper context |
| `chapter-12` | `CLAUDE.md`, slash commands, `Follow` social graph |
| `chapter-13` | MCP server integration |
| `chapter-14` | Plan Mode workflow |
| `chapter-15` | Agent Mode workflow |
| `chapter-16` | Refactoring with AI — compile-test-commit loop |
| `chapter-17` | Hooks and subagents |
| `chapter-18` | Custom slash command library |
| `chapter-19` | Debugging with AI — stack traces, root cause analysis |
| `chapter-20` | Codebase archaeology — git history, architecture mapping |
| `chapter-21` | Dependency and Java version migrations |
| `chapter-22` | SQL query design and index triage |
| `chapter-23` | AI-assisted PR review |
| `chapter-24` | Documentation — README, ADRs, Mermaid diagrams |
| `chapter-25` | Security — JWT ownership checks, input validation |
| `chapter-26` | Cost and model selection |
| `chapter-27` | CI agents — GitHub Actions PR review workflow |
| `chapter-28` | Trust and calibration |
| `chapter-29` | Capstone — `GET /users/{id}/stats` end-to-end |

## Running a chapter

Each chapter requires Docker (for PostgreSQL) and Java 21.

```bash
cd chapter-12
docker compose up -d
mvn spring-boot:run
```

To run tests (H2 in-memory, no Docker needed):

```bash
mvn test
```

## Tech stack

- **Spring Boot 3.4.5** / Java 21
- **Spring Data JPA** + PostgreSQL 16 (Docker)
- **Spring Security** + jjwt 0.12.6 for JWT authentication
- **H2** for `@DataJpaTest` integration tests

## Book

The book walks through building CinéTrack from scratch using GitHub Copilot and Claude Code, covering prompting, context engineering, agent workflows, CI integration, and calibrating trust in AI output.
