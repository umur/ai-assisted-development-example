Add a new REST endpoint to CinéTrack.

Format: METHOD /path - description
Example: GET /movies/:id/top-reviews - return the top 5 reviews by rating for a movie

Endpoint: $ARGUMENTS

Read the relevant controller and service files first to understand existing patterns.
Add:
1. A new method to the service following existing naming and @Transactional conventions
2. A new mapping to the controller that delegates to the service and handles Optional correctly

Do not change any other files. Do not add new entity fields or repositories unless the endpoint clearly requires it and you've confirmed with me first.

After adding the methods, describe what you added in two sentences.
