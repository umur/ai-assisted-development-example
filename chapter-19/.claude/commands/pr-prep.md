You are preparing a pull request for the current CinéTrack branch.

Run these in parallel:

Main session: run `mvn test -q 2>&1 | tail -30` and wait for results.

Subagent task:
1. Run `git diff main...HEAD --stat` to list all changed files
2. Run `git diff main...HEAD` to read all changes
3. For each changed Java file, verify:
   - Entity uses IDENTITY not AUTO
   - Service class is package-private (not public)
   - Controller has one dependency only
   - No FQCNs inline in code
   - No comments except JPA no-arg constructor
4. Note any CLAUDE.md deviations found
5. Draft a PR title (under 70 characters) and a 3-bullet description summarizing what changed

When both are done, report:
- Test results: pass/fail counts, any failing test class names
- Convention audit: deviations found, or "all clear"
- PR draft: title and description ready to paste into GitHub
