# TP5 — JUnit examples (TP5_JUNIT6)

This module contains unit test examples using JUnit Jupiter (JUnit 5), AssertJ for expressive assertions, and Mockito for mocking.

## Learning goals

- Understand unit test best practices: clear names, small fast tests, fixtures, deterministic assertions.
- Write parameterized tests to cover multiple cases without duplication.
- Use lifecycle hooks and test isolation (`@TempDir`) to avoid flaky tests.
- Separate unit and integration tests; run integration tests in CI or on-demand.

---

## Unit tests (quick guide)

Purpose: verify the behavior of a single class or method in isolation.

Good practices

- Fast and deterministic: avoid IO, sleeps and randomness inside unit tests.
- Small scope: one behavior/assertion per test (use `@ParameterizedTest` to avoid duplication).
- Clear names and `@DisplayName` for readability in reports.
- Use `@BeforeEach`/`@AfterEach` for lightweight fixtures; `@BeforeAll`/`@AfterAll` for expensive shared setup.
- Use `@TempDir` for ephemeral filesystem needs.
- Assert using AssertJ for readable failure messages and `assertAll` for grouped checks.

When to use: algorithmic logic, pure functions, helpers, validation rules.

Example patterns (see tests in `src/test/java`):
- Parameterized examples: `@ValueSource`, `@CsvSource`, `@MethodSource`.
- Lifecycle and isolation: `@BeforeEach`, `@TempDir`.
- Timeouts: `assertTimeout` to guarantee fast tests.

---

## Integration tests (quick guide)

Purpose: exercise multiple components together (e.g., service + repository, or HTTP endpoints). They are slower and may require external resources.

Conventions in this project

- Integration tests are named `*IT` (for example `IntegrationExampleIT`) so they can be executed by the Failsafe plugin during the `verify` phase.
- Tag long-running tests with `@Tag("integration")` to select them explicitly in CI.

How to run

```bash
# Run only unit tests (fast):
./mvnw -pl tp5_junit6 -am test

# Run integration tests (Failsafe in verify phase):
./mvnw -pl tp5_junit6 -am verify

# Run tests with inclusion by tag (example: integration):
./mvnw -pl tp5_junit6 -am test -Dgroups=integration
```

Best practices for integration tests

- Keep them fewer in number and focused on real interactions.
- Use testcontainers or ephemeral resources when interacting with databases or network services.
- Clean up external resources reliably and isolate test data.

---

## Mocks with Mockito (short primer)

Why mocks?

- Replace slow or non-deterministic collaborators (DB, network, time, external APIs) with controllable fakes.
- Verify interactions (method calls) and focus tests on the unit's behavior.

Common patterns (see `MockitoExampleTest`):

- Use `@ExtendWith(MockitoExtension.class)` to enable Mockito in JUnit Jupiter.
- Declare collaborators with `@Mock` and inject into the class under test with `@InjectMocks`, or prefer explicit constructor injection in production code and pass mocks in tests.
- Stubbing: `when(mock.method(...)).thenReturn(value)`.
- Verification: `verify(mock).method(...)`, `verifyNoMoreInteractions(mock)`.
- Void methods: `doThrow(...)` / `doNothing()` for stubbing.

Minimal snippet

```java
@ExtendWith(MockitoExtension.class)
class ExampleTest {
  @Mock List<String> mocked;
  @InjectMocks Service service; // or pass mock to constructor
  when(mocked.get(0)).thenReturn("x");
  verify(mocked).get(0);
}
```

Pitfalls and tips

- Avoid over-mocking: prefer using real instances for simple value objects and small helpers.
- Do not mock the class under test; only mock collaborators.
- Keep verification minimal: assert behavior/results first, then interactions if relevant.

---

## Quick commands & tips

- Run unit tests: `./mvnw -pl tp5_junit6 -am test`
- Run integration tests: `./mvnw -pl tp5_junit6 -am verify`
- Build a shaded jar with main demo (if needed): `./mvnw -Pshadedjar -pl tp5_junit6 -am package`

Troubleshooting

- Flaky tests: add `@TempDir`, remove shared mutable state, make tests deterministic.
- Tests not discovered: ensure you use the right naming conventions (`*Test` for surefire, `*IT` for failsafe) and that the surefire/failsafe plugin versions in the parent POM are up-to-date.

---

## Further reading

- JUnit 5 user guide: https://junit.org/junit5/docs/current/user-guide/
- Mockito documentation: https://site.mockito.org/
- AssertJ: https://assertj.github.io/doc/

[← Back to Parent](../index.html)
