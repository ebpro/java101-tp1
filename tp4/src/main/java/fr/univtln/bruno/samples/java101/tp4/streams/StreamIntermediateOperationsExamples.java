package fr.univtln.bruno.samples.java101.tp4.streams;

import fr.univtln.bruno.samples.java101.tp4.Person;
import fr.univtln.bruno.samples.java101.tp4.Product;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Demonstrates intermediate stream operations.
 *
 * <p>Intermediate operations (return Stream):
 * - filter, map, flatMap
 * - distinct, sorted, limit, skip
 * - peek (for debugging)
 * - takeWhile, dropWhile (Java 9+)
 * </p>
 */
@Slf4j
public class StreamIntermediateOperationsExamples {

  private StreamIntermediateOperationsExamples() {
    // Utility class
  }

  /**
   * Demonstrates filter operation.
   */
  public static void filterExamples() {
    log.info("=== Filter Examples ===");

    List<Person> people = List.of(
        new Person("Alice", 25, "alice@example.com"),
        new Person("Bob", 30, null),
        new Person("Charlie", 17, "charlie@example.com"),
        new Person("David", 22, null)
    );

    // Filter adults
    log.info("Adults:");
    people.stream()
        .filter(p -> p.age() >= 18)
        .forEach(p -> log.info("  {}", p.name()));

    // Filter with multiple conditions
    log.info("Adults with email:");
    people.stream()
        .filter(Person::isAdult)
        .filter(p -> p.email() != null)
        .forEach(p -> log.info("  {}", p.name()));

    // Filter numbers
    List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    List<Integer> evenNumbers = numbers.stream()
        .filter(n -> n % 2 == 0)
        .toList();
    log.info("Even numbers: {}", evenNumbers);
  }

  /**
   * Demonstrates map operation.
   */
  public static void mapExamples() {
    log.info("=== Map Examples ===");

    List<Person> people = List.of(
        new Person("Alice", 25),
        new Person("Bob", 30),
        new Person("Charlie", 17)
    );

    // Extract names
    List<String> names = people.stream()
        .map(Person::name)
        .toList();
    log.info("Names: {}", names);

    // Transform to uppercase
    List<String> upperNames = people.stream()
        .map(Person::name)
        .map(String::toUpperCase)
        .toList();
    log.info("Upper names: {}", upperNames);

    // Map to different type
    List<String> descriptions = people.stream()
        .map(p -> p.name() + " is " + p.age() + " years old")
        .toList();
    descriptions.forEach(log::info);

    // mapToInt, mapToLong, mapToDouble for primitives
    int totalAge = people.stream()
        .mapToInt(Person::age)
        .sum();
    log.info("Total age: {}", totalAge);

    double averageAge = people.stream()
        .mapToInt(Person::age)
        .average()
        .orElse(0.0);
    log.info("Average age: {}", averageAge);
  }

  /**
   * Demonstrates flatMap operation.
   */
  public static void flatMapExamples() {
    log.info("=== FlatMap Examples ===");

    // Flatten list of lists
    List<List<Integer>> listOfLists = List.of(
        List.of(1, 2, 3),
        List.of(4, 5),
        List.of(6, 7, 8, 9)
    );

    List<Integer> flattened = listOfLists.stream()
        .flatMap(List::stream)
        .toList();
    log.info("Flattened: {}", flattened);

    // Split strings into words
    List<String> sentences = List.of(
        "Hello world",
        "Java streams",
        "Functional programming"
    );

    List<String> words = sentences.stream()
        .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
        .toList();
    log.info("All words: {}", words);

    // FlatMap with objects
    record Order(String id, List<String> items) {}

    List<Order> orders = List.of(
        new Order("O1", List.of("A", "B")),
        new Order("O2", List.of("C")),
        new Order("O3", List.of("D", "E", "F"))
    );

    List<String> allItems = orders.stream()
        .flatMap(order -> order.items().stream())
        .toList();
    log.info("All items: {}", allItems);

    // flatMapToInt for primitive streams
    long totalItems = orders.stream()
        .flatMapToInt(order -> order.items().stream().mapToInt(item -> 1))
        .sum();
    log.info("Total items: {}", totalItems);
  }

  /**
   * Demonstrates distinct operation.
   */
  public static void distinctExamples() {
    log.info("=== Distinct Examples ===");

    List<String> words = List.of("apple", "banana", "apple", "cherry", "banana", "date");

    List<String> unique = words.stream()
        .distinct()
        .toList();
    log.info("Unique words: {}", unique);

    // Distinct with objects (uses equals/hashCode)
    List<Person> people = List.of(
        new Person("Alice", 25),
        new Person("Bob", 30),
        new Person("Alice", 25), // duplicate
        new Person("Charlie", 17)
    );

    List<Person> uniquePeople = people.stream()
        .distinct()
        .toList();
    log.info("Unique people count: {}", uniquePeople.size());
  }

  /**
   * Demonstrates sorted operation.
   */
  public static void sortedExamples() {
    log.info("=== Sorted Examples ===");

    List<String> words = List.of("banana", "apple", "date", "cherry");

    // Natural order
    List<String> sorted = words.stream()
        .sorted()
        .toList();
    log.info("Sorted: {}", sorted);

    // Reverse order
    List<String> reversed = words.stream()
        .sorted(Comparator.reverseOrder())
        .toList();
    log.info("Reversed: {}", reversed);

    // Sort people by age
    List<Person> people = List.of(
        new Person("Charlie", 30),
        new Person("Alice", 25),
        new Person("Bob", 17)
    );

    List<Person> sortedByAge = people.stream()
        .sorted(Comparator.comparing(Person::age))
        .toList();
    log.info("Sorted by age:");
    sortedByAge.forEach(p -> log.info("  {} - {}", p.name(), p.age()));

    // Multiple comparators
    List<Person> multiSort = people.stream()
        .sorted(Comparator.comparing(Person::age)
            .thenComparing(Person::name))
        .toList();
    log.info("Multi-sort:");
    multiSort.forEach(p -> log.info("  {} - {}", p.name(), p.age()));
  }

  /**
   * Demonstrates limit and skip operations.
   */
  public static void limitAndSkipExamples() {
    log.info("=== Limit and Skip Examples ===");

    List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    // Take first 5
    List<Integer> first5 = numbers.stream()
        .limit(5)
        .toList();
    log.info("First 5: {}", first5);

    // Skip first 5
    List<Integer> after5 = numbers.stream()
        .skip(5)
        .toList();
    log.info("After skipping 5: {}", after5);

    // Pagination: skip and limit
    int pageSize = 3;
    int pageNumber = 2; // 0-indexed

    List<Integer> page = numbers.stream()
        .skip((long) pageNumber * pageSize)
        .limit(pageSize)
        .toList();
    log.info("Page {} (size {}): {}", pageNumber, pageSize, page);
  }

  /**
   * Demonstrates peek operation (for debugging).
   */
  public static void peekExamples() {
    log.info("=== Peek Examples ===");

    List<String> words = List.of("apple", "banana", "cherry");

    // Peek to debug stream pipeline
    List<String> result = words.stream()
        .peek(w -> log.debug("Original: {}", w))
        .map(String::toUpperCase)
        .peek(w -> log.debug("Upper: {}", w))
        .filter(w -> w.length() > 5)
        .peek(w -> log.debug("Filtered: {}", w))
        .toList();

    log.info("Final result: {}", result);

    // Warning: peek is for side effects, not for business logic
    // Don't do this:
    List<String> bad = new ArrayList<>();
    words.stream()
        .peek(bad::add) // BAD: side effect for result
        .count();

    // Do this instead:
    List<String> good = words.stream()
        .collect(Collectors.toList());
  }

  /**
   * Demonstrates takeWhile and dropWhile (Java 9+).
   */
  public static void takeWhileDropWhileExamples() {
    log.info("=== TakeWhile and DropWhile Examples ===");

    List<Integer> numbers = List.of(2, 4, 6, 8, 3, 10, 12);

    // takeWhile: take elements while predicate is true
    List<Integer> taken = numbers.stream()
        .takeWhile(n -> n % 2 == 0)
        .toList();
    log.info("Taken while even: {}", taken); // [2, 4, 6, 8]

    // dropWhile: drop elements while predicate is true
    List<Integer> dropped = numbers.stream()
        .dropWhile(n -> n % 2 == 0)
        .toList();
    log.info("Dropped while even: {}", dropped); // [3, 10, 12]

    // Sorted stream example
    List<Integer> sorted = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    List<Integer> lessThan5 = sorted.stream()
        .takeWhile(n -> n < 5)
        .toList();
    log.info("Less than 5: {}", lessThan5);

    List<Integer> from5 = sorted.stream()
        .dropWhile(n -> n < 5)
        .toList();
    log.info("From 5 onwards: {}", from5);
  }
}

