# ADR-001: Package-Private Service Classes

## Status

Accepted

## Context

CinéTrack uses a feature-package architecture: `com.cinetrack.watchlist`, `com.cinetrack.review`, etc. Each feature package is self-contained and contains all the types needed for that feature: entity, repository, service, controller, request, response, and mapper.

Services contain the business logic and are the only layer that accesses repositories. Controllers delegate to their corresponding service; services depend on repositories but never on other services.

A decision was needed about service class visibility: `public` (accessible from any package) or package-private (the Java default, accessible only within the declaring package).

## Decision

Service classes are package-private. The `public` modifier is omitted from service class declarations.

```java
// Correct — package-private
@Service
class WatchlistService { ... }

// Incorrect — not used in CinéTrack
@Service
public class WatchlistService { ... }
```

## Consequences

**Positive:**

- Services cannot be injected into other services across package boundaries. This enforces the architectural rule that services depend only on repositories, making cross-service dependencies a compile error rather than a code review concern.
- The constraint is enforced by the compiler, not by convention or vigilance. New engineers who attempt to inject a service into another service get a clear error.
- The package boundary makes the feature-package architecture explicit: each package is a self-contained unit.

**Negative:**

- Test classes must be in the same package as the service to access it (e.g., `WatchlistServiceTest` in `com.cinetrack.watchlist`). Test classes in other packages cannot inject the service directly.
- Spring Boot's component scanning works correctly with package-private classes, but this is not universally known — engineers unfamiliar with the pattern may be confused initially.

**Neutral:**

- Spring proxying (for `@Transactional`, AOP) works correctly with package-private classes when using CGLIB-based proxying, which Spring Boot uses by default.
- Controllers are also package-private, consistent with the same reasoning.
