package fr.univtln.bruno.samples.java101.tp4.functionalinterfaces;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates creating custom functional interfaces.
 *
 * <p>Shows:
 * - @FunctionalInterface annotation
 * - Default methods in functional interfaces
 * - Static methods in functional interfaces
 * - Custom generic functional interfaces
 * </p>
 */
@Slf4j
public class CustomFunctionalInterfacesExamples {

  private CustomFunctionalInterfacesExamples() {
    // Utility class
  }

  /**
   * Simple functional interface with one abstract method.
   */
  @FunctionalInterface
  public interface Validator<T> {
    /**
     * Validates the input.
     *
     * @param input the value to validate
     * @return true if valid, false otherwise
     */
    boolean isValid(T input);

    /**
     * Default method: negates this validator.
     *
     * @return a new Validator that returns opposite result
     */
    default Validator<T> negate() {
      return input -> !this.isValid(input);
    }

    /**
     * Default method: combines this with another validator using AND.
     *
     * @param other the other validator
     * @return a new Validator that requires both to be true
     */
    default Validator<T> and(Validator<T> other) {
      return input -> this.isValid(input) && other.isValid(input);
    }

    /**
     * Static factory method for common validators.
     *
     * @param <T> the type to validate
     * @return a validator that accepts non-null values
     */
    static <T> Validator<T> notNull() {
      return input -> input != null;
    }
  }

  /**
   * Functional interface for transformations with exception handling.
   */
  @FunctionalInterface
  public interface CheckedFunction<T, R> {
    /**
     * Applies this function.
     *
     * @param input the input
     * @return the result
     * @throws Exception if something goes wrong
     */
    R apply(T input) throws Exception;

    /**
     * Wraps this checked function into an unchecked one.
     *
     * @return an unchecked function that wraps exceptions
     */
    default java.util.function.Function<T, R> unchecked() {
      return input -> {
        try {
          return apply(input);
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      };
    }
  }

  /**
   * Functional interface for operations that might fail.
   */
  @FunctionalInterface
  public interface Fallible<T> {
    /**
     * Executes the operation.
     *
     * @return the result
     * @throws Exception if operation fails
     */
    T execute() throws Exception;

    /**
     * Executes with a fallback value on failure.
     *
     * @param fallback the fallback value
     * @return the result or fallback
     */
    default T orElse(T fallback) {
      try {
        return execute();
      } catch (Exception e) {
        log.warn("Operation failed, using fallback", e);
        return fallback;
      }
    }

    /**
     * Executes with a fallback supplier on failure.
     *
     * @param fallbackSupplier the fallback supplier
     * @return the result or fallback
     */
    default T orElseGet(java.util.function.Supplier<T> fallbackSupplier) {
      try {
        return execute();
      } catch (Exception e) {
        log.warn("Operation failed, computing fallback", e);
        return fallbackSupplier.get();
      }
    }
  }

  /**
   * Demonstrates using custom Validator interface.
   */
  public static void customValidatorExample() {
    log.info("=== Custom Validator Example ===");

    // Create validators
    Validator<String> notNull = Validator.notNull();
    Validator<String> notEmpty = s -> !s.isEmpty();
    Validator<String> notBlank = s -> !s.isBlank();
    Validator<String> minLength = s -> s.length() >= 3;

    // Combine validators
    Validator<String> validName = notNull
        .and(notEmpty)
        .and(notBlank)
        .and(minLength);

    List<String> names = List.of("Alice", "Bo", "  ", "", "Charlie");

    log.info("Valid names:");
    names.stream()
        .filter(validName::isValid)
        .forEach(log::info);

    // Negate validator
    Validator<String> invalidName = validName.negate();

    log.info("Invalid names:");
    names.stream()
        .filter(invalidName::isValid)
        .forEach(log::info);
  }

  /**
   * Demonstrates using checked function interface.
   */
  public static void checkedFunctionExample() {
    log.info("=== Checked Function Example ===");

    // Function that might throw checked exception
    CheckedFunction<String, Integer> parseInteger = Integer::parseInt;

    List<String> inputs = List.of("42", "123", "not-a-number", "999");

    // Convert to unchecked function for use in streams
    List<Integer> numbers = new ArrayList<>();
    for (String input : inputs) {
      try {
        numbers.add(parseInteger.apply(input));
      } catch (Exception e) {
        log.warn("Failed to parse: {}", input);
      }
    }

    log.info("Parsed numbers: {}", numbers);

    // Another example with file reading simulation
    CheckedFunction<String, String> readFile = filename -> {
      if (filename.endsWith(".txt")) {
        return "Content of " + filename;
      }
      throw new Exception("Not a text file");
    };

    List<String> files = List.of("data.txt", "config.xml", "readme.txt");

    files.forEach(file -> {
      try {
        String content = readFile.apply(file);
        log.info(content);
      } catch (Exception e) {
        log.warn("Cannot read {}: {}", file, e.getMessage());
      }
    });
  }

  /**
   * Demonstrates using Fallible interface.
   */
  public static void fallibleExample() {
    log.info("=== Fallible Example ===");

    // Operation that might fail
    Fallible<Integer> riskyOperation = () -> {
      if (Math.random() > 0.5) {
        return 42;
      }
      throw new Exception("Operation failed");
    };

    // Use with fallback value
    Integer result1 = riskyOperation.orElse(-1);
    log.info("Result with fallback value: {}", result1);

    // Use with fallback supplier
    Integer result2 = riskyOperation.orElseGet(() -> {
      log.info("Computing fallback value...");
      return 0;
    });
    log.info("Result with fallback supplier: {}", result2);

    // Multiple attempts with different strategies
    Fallible<String> connectToService = () -> {
      if (Math.random() > 0.7) {
        return "Connected";
      }
      throw new Exception("Connection failed");
    };

    String connection = connectToService.orElse("Offline");
    log.info("Connection status: {}", connection);
  }

  /**
   * Demonstrates multi-parameter functional interface.
   */
  @FunctionalInterface
  public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);

    default <W> TriFunction<T, U, V, W> andThen(java.util.function.Function<R, W> after) {
      return (t, u, v) -> after.apply(apply(t, u, v));
    }
  }

  /**
   * Demonstrates custom TriFunction.
   */
  public static void triFunctionExample() {
    log.info("=== TriFunction Example ===");

    TriFunction<String, Integer, String, String> formatPerson =
        (name, age, city) -> String.format("%s is %d years old and lives in %s", name, age, city);

    String description = formatPerson.apply("Alice", 25, "Paris");
    log.info(description);

    // With andThen
    TriFunction<String, Integer, String, String> formatAndUpper =
        formatPerson.andThen(String::toUpperCase);

    String upperDescription = formatAndUpper.apply("Bob", 30, "London");
    log.info(upperDescription);
  }
}

