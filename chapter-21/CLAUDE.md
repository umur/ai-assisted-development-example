# CinéTrack — Claude Code Project Context

## Project

CinéTrack is a Spring Boot 4 / Java 21 REST API for tracking movies,
reviews, and social connections. PostgreSQL 16 in Docker for persistence.
JWT for stateless authentication.

## Tech Stack

- Spring Boot 4.0.5, Java 21 LTS
- spring-boot-starter-web, data-jpa, security, validation
- jjwt 0.12.6 — use `verifyWith()` and `parseSignedClaims()`, not the
  old 0.11.x API (`setSigningKey`, `parseClaimsJws`, `getBody`)
- PostgreSQL 16 via Docker Compose; Testcontainers 2.x for integration tests (PostgreSQLContainer + @ServiceConnection)

## Architecture

Features are package-organized: `com.cinetrack.[feature]`.
Each feature package contains: Entity, Repository (interface),
Service (class), Controller, Request record, Response record, Mapper.

## Entity Conventions

- `@GeneratedValue(strategy = GenerationType.IDENTITY)` — never AUTO
- Table names: plural snake_case — `app_users`, `watchlists`, `reviews`, `follows`
- FK columns: field_id pattern — `owner_id`, `author_id`, `follower_id`
- `@ManyToOne` for ownership and relationships — not bare `Long` foreign-key fields
- Equals/hashCode: id-based (Vlad Mihalcea pattern); `hashCode` returns `getClass().hashCode()`
- `protected` no-arg constructor for JPA; public domain constructor with required fields

## Service Conventions

- Package-private class: `class FooService` (not `public`)
- `@Service` on the class
- Constructor injection; no field injection
- `@Transactional` on all write methods
- `@Transactional(readOnly = true)` on read methods
- Methods that may not find their target return `Optional<FooResponse>`
- Delete methods return `boolean` (true = deleted, false = not found)
- Services depend on repositories; never on other services

## Controller Conventions

- Package-private class: `class FooController` (not `public`)
- `@RestController`, `@RequestMapping("/foo")`
- One dependency: the corresponding `FooService`
- No business logic — delegates to service, maps `Optional` to `ResponseEntity`
- 201 for creation, 204 for delete, 404 for not found

## Code Style

- No Lombok. Plain Java getters/setters only.
- No FQCNs inline. Always add import statements.
- No comments except the JPA no-arg constructor (`// JPA`)
- No `try-catch` in controllers or services unless catching a specific
  documented exception type with a specific semantic reason

## Testing

- `@SpringBootTest(webEnvironment = NONE)` + `@Transactional` + `@Testcontainers` for service/repository integration tests with real PostgreSQL
- Assert on observable behavior (return values, DB state), not on mock calls
- Cover all failure paths: not-found, no-op collection operations, boundary cases
