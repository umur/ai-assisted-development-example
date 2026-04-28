You are adding a new feature to CinéTrack.

Feature name: $ARGUMENTS

**Read these archetype files first:**

- src/main/java/com/cinetrack/watchlist/Watchlist.java
- src/main/java/com/cinetrack/watchlist/WatchlistService.java

**Generate the following files in `com.cinetrack.[featurename]`:**

1. `[Feature].java` — entity following Watchlist.java conventions
2. `[Feature]Repository.java` — JpaRepository extension with relevant derived queries
3. `[Feature]Request.java` — record with input fields
4. `[Feature]Response.java` — record with output fields
5. `[Feature]Mapper.java` — package-private static mapping class
6. `[Feature]Service.java` — service following WatchlistService.java conventions
7. `[Feature]Controller.java` — controller following the conventions in CLAUDE.md

Apply all conventions from CLAUDE.md. Generate only the files listed above.
Do not generate test files, migration scripts, or configuration changes unless
explicitly requested in the feature description.
