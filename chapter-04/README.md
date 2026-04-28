# CinéTrack — Chapter 4 Snapshot

Cumulative snapshot after **Chapter 4: Copilot in the IDE: Inline Completion as a Skill**.

## What's new in this chapter

| File | Added by |
|---|---|
| `MovieController.java` | Copilot inline completion (comment + type driven) |
| `MovieRequest.java` | Copilot inline completion (record, type driven) |
| `MovieResponse.java` | Copilot inline completion (record, type driven) |
| `MovieMapper.java` | Copilot inline completion (comment driven) |

## Running

```bash
docker-compose up -d
./mvnw spring-boot:run
```

Postgres starts on `localhost:5432`. The app starts on `localhost:8080`.

## Endpoints

| Method | Path | Description |
|---|---|---|
| GET | `/movies` | Paginated list of all movies |
| GET | `/movies/{id}` | Single movie by id, 404 if not found |
| POST | `/movies` | Create a movie from `MovieRequest` body |
| PUT | `/movies/{id}` | Update a movie, 404 if not found |
| DELETE | `/movies/{id}` | Delete a movie, 204 on success, 404 if not found |

## Carried forward from Chapter 3

- `Movie` entity, `MovieRepository` with `findByYear`
- Docker Compose + Postgres 16
- Spring Boot 4, JDK 21, Jakarta Persistence, plain Java, no Lombok
