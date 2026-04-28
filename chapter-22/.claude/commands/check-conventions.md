Audit all Java files changed in the current git diff against CinéTrack conventions.

Run `git diff --name-only HEAD` to identify changed files.
For each changed .java file, check:

Entity:
- @GeneratedValue uses IDENTITY, not AUTO
- Table name is plural snake_case
- FK columns follow field_id pattern (owner_id, author_id, etc.)
- @ManyToOne for relationships, not bare Long fields
- Equals/hashCode is id-based; hashCode returns getClass().hashCode()
- Protected no-arg constructor with // JPA comment
- No Lombok

Service:
- Class is package-private (no public modifier)
- @Service on the class
- Constructor injection only, no @Autowired fields
- @Transactional on all write methods
- @Transactional(readOnly=true) on all read methods
- Returns Optional<FooResponse> for methods that may not find their target
- Delete methods return boolean

Controller:
- Class is package-private
- @RestController and @RequestMapping present
- Exactly one dependency: the corresponding service
- No business logic in the controller
- 201 for creation, 204 for delete, 404 for not found

Code style:
- No FQCNs inline (always import)
- No comments except // JPA on the no-arg constructor
- No try-catch unless catching a specific documented exception

Report violations by file. For each violation: file path, line number if possible, what's wrong, what the correct pattern is.
If no violations, report "all clear."
