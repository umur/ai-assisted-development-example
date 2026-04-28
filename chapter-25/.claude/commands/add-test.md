Generate @DataJpaTest integration tests for $ARGUMENTS.

Read these files first:
- src/test/java/com/cinetrack/watchlist/WatchlistServiceTest.java (the archetype)
- The source file for $ARGUMENTS to understand its public methods

Generate a test class in the same test package as the class being tested.
Follow these rules exactly:
- @DataJpaTest on the class
- Construct the service under test directly using repository beans (not @Autowired on the service)
- No mocking — use real H2 database
- Cover every public method: the happy path and all failure paths (not found, empty collection, boundary values)
- Method names: verb_subject_condition pattern (e.g., create_returnsEmpty_whenUserNotFound)
- Assert on return values and database state, not on method calls

Generate only the test file. Do not change production code.
