# CinéTrack — Chapter 5 Snapshot

Cumulative snapshot after **Chapter 5: The Prompt Loop: Iterating with Claude Code**.

## What's new in this chapter

| File | Added by |
|---|---|
| `AppUser.java` | Claude Code iterative session (ask/read/redirect) |
| `AppUserRepository.java` | Claude Code iterative session |
| `LoginRequest.java` | Claude Code iterative session |
| `LoginResponse.java` | Claude Code iterative session |
| `JwtService.java` | Claude Code iterative session |
| `SecurityConfig.java` | Claude Code iterative session (narrowing redirect on UserDetailsService) |
| `AuthController.java` | Claude Code iterative session (narrowing redirect on register endpoint) |

## Running

```bash
docker-compose up -d
./mvnw spring-boot:run
```

Postgres starts on `localhost:5432`. The app starts on `localhost:8080`.

## Endpoints

| Method | Path | Auth required | Description |
|---|---|---|---|
| POST | `/auth/login` | No | Returns JWT token |
| GET | `/movies` | Yes | Paginated movie list |
| GET | `/movies/{id}` | Yes | Single movie |
| POST | `/movies` | Yes | Create movie |
| PUT | `/movies/{id}` | Yes | Update movie |
| DELETE | `/movies/{id}` | Yes | Delete movie |

## Notes

- `jwt.secret` in `application.yml` is a placeholder. Replace with a real 256-bit base64-encoded key before running outside local development.
- The JWT filter (validating tokens on incoming requests) is added in chapter 9. For now, Spring Security protects all `/movies/**` endpoints but does not validate JWT claims on each request.

## Carried forward from Chapter 4

- `Movie` entity, `MovieRepository`, `MovieController`, DTOs, mapper
- Docker Compose + Postgres 16
- Spring Boot 4, JDK 21, Jakarta Persistence, plain Java, no Lombok
