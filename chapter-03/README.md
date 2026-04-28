# CinéTrack — Chapter 03 Snapshot

State of the project at the end of chapter 3 (*Your First Prompts: From Question to Working Code*). The reader has used Copilot and Claude Code to build the first domain entity and its repository, prompt by prompt.

## Run

```bash
docker compose up -d
mvn spring-boot:run
```

The application boots on port 8080. There are still no controllers (those start in chapter 4), but Hibernate now creates a `movies` table on startup against the Postgres container.

## Test

```bash
mvn test
```

Runs `MovieRepositoryTest` against an in-memory H2 database via `@DataJpaTest`. H2 is a chapter-3 convenience so the test suite has zero infrastructure prerequisites; chapter 9 swaps it for Testcontainers.

## What changed in this chapter

- `src/main/java/com/cinetrack/movie/Movie.java` — first JPA entity (`id`, `title`, `year`, `director`). The `year` field maps to a `release_year` column to dodge the SQL reserved word.
- `src/main/java/com/cinetrack/movie/MovieRepository.java` — `JpaRepository<Movie, Long>` with a derived `findByYear(Integer, Pageable)` query
- `src/test/java/com/cinetrack/movie/MovieRepositoryTest.java` — `@DataJpaTest` exercising `findByYear` against H2
- `src/test/resources/application.yml` — test-only H2 configuration so `@DataJpaTest` does not try to talk to Postgres
- `pom.xml` — added `spring-boot-data-jpa-test`, `spring-boot-jdbc-test`, and `com.h2database:h2` at `test` scope so the repository test runs without Postgres

Both `Movie` and `MovieRepository` were produced through the conversation walkthroughs in §3.5 (Movie entity) and §3.6 (MovieRepository).
