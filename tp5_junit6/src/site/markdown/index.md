# TP5 — JUnit examples (TP5_JUNIT6)

This module contains unit test examples using JUnit Jupiter (JUnit 5) and AssertJ for assertions.

## Learning goals

- Understand unit test best practices: clear names, small fast tests, fixtures, deterministic assertions.
- Write parameterized tests to cover multiple cases without duplication.
- Use lifecycle hooks and test isolation (`@TempDir`) to avoid flaky tests.
- Separate unit and integration tests; run integration tests in CI or on-demand.

## How to run

From project root:

```bash
# Run unit tests for the module (fast)
./mvnw -pl tp5_junit6 -am test

# Run integration tests (Failsafe) during verify phase
./mvnw -pl tp5_junit6 -am verify

# Run only tests tagged as integration
./mvnw -pl tp5_junit6 -am test -Dgroups=integration
```

## Quick best-practices (TL;DR)

- Keep unit tests fast (<100ms ideally) and deterministic. Avoid sleeps and randomness.
- Use `@TempDir` for filesystem isolation and avoid writing into shared system directories.
- Use `assertTimeout` for timing guarantees but keep timeouts generous enough for CI.
- Favor fluent assertions (AssertJ) for expressive error messages.
- Use parameterized tests for repeated cases and `@Nested` to group related behavior.

[← Back to Parent](../index.html)
