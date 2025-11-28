package fr.univtln.bruno.samples.java101.tp5junit6;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

/**
 * Pedagogical examples for unit testing with JUnit Jupiter (best practices):
 * - clear test names / @DisplayName
 * - lifecycle hooks (@BeforeAll, @BeforeEach, ...)
 * - grouped assertions (assertAll)
 * - AssertJ fluent assertions
 * - parameterized tests (ValueSource, CsvSource, MethodSource)
 * - timeouts, exception testing
 * - @TempDir for filesystem isolation
 * - @Nested tests for better organization
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("TP5: Unit test examples — JUnit Jupiter best practices")
class TestExample {

  @BeforeAll
  void beforeAll() {
    // expensive shared setup (run once per class)
  }

  @AfterAll
  void afterAll() {
    // cleanup
  }

  @BeforeEach
  void beforeEach(TestInfo testInfo) {
    // runs before each test — use TestInfo to log context
  }

  @AfterEach
  void afterEach() {
    // runs after each test
  }

  @Test
  @DisplayName("simple arithmetic with AssertJ and grouped assertions")
  void simpleAssertion() {
    int result = 1 + 1;

    // AssertJ fluent assertion
    assertThat(result).isEqualTo(2);

    // Grouped assertions with JUnit (all assertions executed)
    assertAll("multiple checks",
        () -> assertThat(result).isPositive(),
        () -> assertThat(result).isLessThan(10)
    );
  }

  @Test
  @DisplayName("expecting an exception — defensive tests")
  void exceptionTest() {
    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
      throw new IllegalArgumentException("bad input");
    });

    assertThat(ex).hasMessageContaining("bad input");
  }

  @Test
  @DisplayName("timeout example — keep tests fast and deterministic")
  void timeoutExample() {
    // Prefer assertTimeout (doesn't kill thread) for fast checks
    String res = assertTimeout(Duration.ofMillis(200), () -> {
      // Simulate fast work
      return "ok";
    });

    assertThat(res).isEqualTo("ok");
  }

  @ParameterizedTest(name = "value={0}")
  @ValueSource(strings = {"alpha", "beta", "gamma"})
  @DisplayName("parameterized test — ValueSource example")
  void parameterized(String value) {
    assertThat(value).isNotBlank();
  }

  @ParameterizedTest(name = "{0} + {1} => {2}")
  @CsvSource({"1,1,2", "2,3,5", "10,5,15"})
  @DisplayName("parameterized test — CsvSource example")
  void csvParameterized(int a, int b, int expected) {
    assertThat(a + b).isEqualTo(expected);
  }

  static Stream<String> provideStrings() {
    return Stream.of("x", "y", "z");
  }

  @ParameterizedTest
  @DisplayName("parameterized via MethodSource")
  @org.junit.jupiter.params.provider.MethodSource("provideStrings")
  void methodSourceParameterized(String s) {
    assertThat(s).hasSize(1);
  }

  @Nested
  @DisplayName("Filesystem isolated tests")
  class FileTests {

    @Test
    @DisplayName("use @TempDir for filesystem isolation")
    void tempDirExample(@TempDir Path tempDir) throws Exception {
      Path file = tempDir.resolve("sample.txt");
      Files.writeString(file, "hello");

      assertThat(Files.exists(file)).isTrue();
      assertThat(Files.readString(file)).isEqualTo("hello");
    }
  }

  @Nested
  @DisplayName("Organizing related behavior with Nested")
  class MathBehavior {

    @DisplayName("repeating a simple check")
    @RepeatedTest(3)
    void repeatedTest() {
      assertThat(2 * 2).isEqualTo(4);
    }
  }

}
