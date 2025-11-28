package fr.univtln.bruno.samples.java101.tp4.lambda;

import fr.univtln.bruno.samples.java101.tp4.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Demonstrates lambda expressions and their evolution from anonymous classes.
 *
 * <p>Shows:
 * - Anonymous class vs lambda syntax
 * - Different lambda forms (expression vs block)
 * - Type inference
 * - Effectively final variables in lambda closures
 * </p>
 */
@Slf4j
public class LambdaBasicsExamples {

  private LambdaBasicsExamples() {
    // Utility class
  }

  /**
   * Demonstrates evolution from anonymous class to lambda.
   */
  public static void anonymousClassToLambda() {
    log.info("=== Anonymous Class to Lambda ===");

    List<String> names = List.of("Alice", "Bob", "Charlie", "David");

    // Old style: anonymous class
    names.forEach(new Consumer<String>() {
      @Override
      public void accept(String name) {
        log.info("Hello, {}!", name);
      }
    });

    // Lambda expression style
    names.forEach(name -> log.info("Hello, {}!", name));

    // Method reference (most concise)
    names.forEach(log::info);
  }

  /**
   * Demonstrates different lambda syntax forms.
   */
  public static void lambdaSyntaxVariations() {
    log.info("=== Lambda Syntax Variations ===");

    List<Person> people = List.of(
        new Person("Alice", 25, "alice@example.com"),
        new Person("Bob", 30, "bob@example.com"),
        new Person("Charlie", 17, "charlie@example.com")
    );

    // 1. Expression lambda (single expression, no braces)
    Predicate<Person> isAdult1 = p -> p.age() >= 18;

    // 2. Block lambda (with braces and return)
    Predicate<Person> isAdult2 = p -> {
      log.debug("Checking if {} is adult", p.name());
      return p.age() >= 18;
    };

    // 3. With explicit type (usually inferred)
    Predicate<Person> isAdult3 = (Person p) -> p.age() >= 18;

    long adultCount = people.stream().filter(isAdult1).count();
    log.info("Adult count: {}", adultCount);
  }

  /**
   * Demonstrates variable capture in lambdas (effectively final).
   */
  public static void lambdaClosures() {
    log.info("=== Lambda Closures ===");

    int threshold = 18; // effectively final
    List<Person> people = List.of(
        new Person("Alice", 25),
        new Person("Bob", 30),
        new Person("Charlie", 17)
    );

    // Lambda captures 'threshold' from enclosing scope
    Predicate<Person> isAboveThreshold = p -> p.age() >= threshold;

    people.stream()
        .filter(isAboveThreshold)
        .forEach(p -> log.info("{} is above threshold", p.name()));

    // This would cause compilation error - threshold must be effectively final:
    // threshold = 20; // ERROR: cannot modify captured variable
  }

  /**
   * Demonstrates composing functions with lambdas.
   */
  public static void composingFunctions() {
    log.info("=== Composing Functions ===");

    Function<String, String> trim = String::trim;
    Function<String, String> upperCase = String::toUpperCase;
    Function<String, String> addExclamation = s -> s + "!";

    // Compose: apply in sequence
    Function<String, String> process = trim
        .andThen(upperCase)
        .andThen(addExclamation);

    String result = process.apply("  hello world  ");
    log.info("Processed: {}", result); // "HELLO WORLD!"

    // compose() applies in reverse order
    Function<Integer, Integer> multiplyBy2 = x -> x * 2;
    Function<Integer, Integer> add3 = x -> x + 3;

    // compose: first add3, then multiplyBy2
    Function<Integer, Integer> combined = multiplyBy2.compose(add3);
    log.info("Result: {}", combined.apply(5)); // (5 + 3) * 2 = 16
  }

  /**
   * Demonstrates chaining predicates.
   */
  public static void chainingPredicates() {
    log.info("=== Chaining Predicates ===");

    List<Person> people = List.of(
        new Person("Alice", 25, "alice@example.com"),
        new Person("Bob", 30, null),
        new Person("Charlie", 17, "charlie@example.com"),
        new Person("David", 22, null)
    );

    Predicate<Person> isAdult = p -> p.age() >= 18;
    Predicate<Person> hasEmail = p -> p.email() != null;

    // Combine predicates with and()
    Predicate<Person> isAdultWithEmail = isAdult.and(hasEmail);

    log.info("Adults with email:");
    people.stream()
        .filter(isAdultWithEmail)
        .forEach(p -> log.info("  - {}", p.name()));

    // Combine with or()
    Predicate<Person> isAdultOrHasEmail = isAdult.or(hasEmail);

    // Negate
    Predicate<Person> isNotAdult = isAdult.negate();

    log.info("Minors:");
    people.stream()
        .filter(isNotAdult)
        .forEach(p -> log.info("  - {}", p.name()));
  }

  /**
   * Demonstrates sorting with lambda comparators.
   */
  public static void lambdaComparators() {
    log.info("=== Lambda Comparators ===");

    List<Person> people = new ArrayList<>(List.of(
        new Person("Charlie", 30),
        new Person("Alice", 25),
        new Person("Bob", 30),
        new Person("David", 25)
    ));

    // Sort by age, then by name
    people.sort(
        Comparator.comparing(Person::age)
            .thenComparing(Person::name)
    );

    log.info("Sorted by age, then name:");
    people.forEach(p -> log.info("  {} - {}", p.name(), p.age()));

    // Reverse order
    people.sort(Comparator.comparing(Person::age).reversed());

    log.info("Sorted by age (descending):");
    people.forEach(p -> log.info("  {} - {}", p.name(), p.age()));
  }
}

