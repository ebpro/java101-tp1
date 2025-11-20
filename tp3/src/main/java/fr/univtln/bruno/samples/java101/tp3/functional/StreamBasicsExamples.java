package fr.univtln.bruno.samples.java101.tp3.functional;

import fr.univtln.bruno.samples.java101.tp3.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Basic Stream API examples focused on collections: creation, intermediate operations and common idioms.
 */
@Slf4j
public class StreamBasicsExamples {
  /**
   * Private constructor to prevent instantiation.
   */
  private StreamBasicsExamples() {
  }

  /**
   * Demonstrates various ways to create streams from collections and primitives.
   */
  public static void streamCreationExample() {
    log.info("=== Stream Creation ===");
    List<String> fromList = List.of("A", "B", "C");
    log.debug("fromList snapshot: {}", fromList);
    log.info("From list: {}", fromList.stream().toList());
    log.info("From array: {}", Arrays.stream(new String[]{"X", "Y"}).toList());
    log.info("From values: {}", Stream.of("1", "2", "3").toList());
    log.info("Empty count: {}", Stream.empty().count());
    log.info("Iterate limit: {}", Stream.iterate(0, n -> n + 1).limit(5).toList());
    log.info("Generate random: {}", Stream.generate(Math::random).limit(3).toList());
    log.info("Range: {}", IntStream.range(1, 5).boxed().toList());
    log.info("RangeClosed: {}", IntStream.rangeClosed(1, 5).boxed().toList());

    // Student note: infinite streams (Stream.generate / Stream.iterate without limit) can exhaust memory
    // or run indefinitely if not limited. Always apply limit() or takeWhile() when experimenting.
  }

  /**
   * Demonstrates filtering a collection using stream filters.
   */
  public static void filterExample() {
    log.info("=== Filter Example ===");
    List<Person> people = List.of(
      Person.of("Alice", "Smith", 30),
      Person.of("Bob", "Jones", 25),
      Person.of("Charlie", "Brown", 35),
      Person.of("David", "Wilson", 28)
    );
    log.debug("people snapshot: {}", people);
    List<String> age30 = people.stream().filter(p -> p.age() >= 30).map(Person::getFullName).toList();
    log.debug("Filtered age>=30 count: {}", age30.size());
    log.info("Age >=30: {}", age30);
    List<String> complex = people.stream().filter(p -> p.age() >= 25).filter(p -> p.lastName().length() > 5).map(Person::getFullName).toList();
    log.debug("Filtered complex pipeline count: {}", complex.size());
    log.info("Age>=25 & long lastName: {}", complex);
  }

  /**
   * Demonstrates mapping operations on streams (map, method references).
   */
  public static void mapExample() {
    log.info("=== Map Example ===");
    List<Person> people = List.of(Person.of("Alice", "Smith", 30), Person.of("Bob", "Jones", 25));
    log.info("Full names: {}", people.stream().map(Person::getFullName).toList());
    log.info("Ages: {}", people.stream().map(Person::age).toList());
    log.info("Upper: {}", people.stream().map(Person::getFullName).map(String::toUpperCase).toList());
  }

  /**
   * Demonstrates flatMap for nested collections and splitting strings.
   */
  public static void flatMapExample() {
    log.info("=== FlatMap Example ===");
    List<List<String>> nested = List.of(List.of("A", "B"), List.of("C"));
    log.info("Flatten: {}", nested.stream().flatMap(Collection::stream).toList());
    List<String> sentences = List.of("Hello World", "Java Streams");
    log.info("Words: {}", sentences.stream().flatMap(s -> Arrays.stream(s.split(" "))).toList());
  }

  /**
   * Demonstrates distinct/sorted/limit/skip stream terminal operations.
   */
  public static void distinctSortedLimitExample() {
    log.info("=== Distinct/Sorted/Limit ===");
    List<Integer> nums = List.of(5, 2, 8, 2, 9, 1, 5);
    log.info("Distinct: {}", nums.stream().distinct().toList());
    log.info("Sorted: {}", nums.stream().sorted().toList());
    log.info("Top3 desc distinct: {}", nums.stream().distinct().sorted(Comparator.reverseOrder()).limit(3).toList());
    log.info("Skip first 2 sorted: {}", nums.stream().sorted().skip(2).toList());
  }

  /**
   * Demonstrates use of peek for debugging intermediate pipeline state.
   */
  public static void peekExample() {
    log.info("=== Peek Example ===");
    List<Integer> nums = List.of(1, 2, 3, 4, 5);
    log.debug("nums initial: {}", nums);
    List<Integer> result = nums.stream()
      .peek(n -> log.debug("Original {}", n))
      .filter(n -> n % 2 == 0)
      .peek(n -> log.debug("Filtered {}", n))
      .map(n -> n * 2)
      .peek(n -> log.debug("Mapped {}", n))
      .toList();
    log.info("Result {}", result);

    // Student note: Stream.toList() (since Java 16) may return an unmodifiable list; Collectors.toList()
    // historically returned a mutable list. If code expects mutability, use new ArrayList<>(stream.toList())
    // or Collectors.toCollection(ArrayList::new).
  }

  /**
   * Demonstrates different sorting idioms using streams and comparators.
   */
  public static void sortingExample() {
    log.info("=== Sorting Example ===");
    List<Person> people = List.of(Person.of("Charlie", "Brown", 35), Person.of("Alice", "Smith", 30), Person.of("Bob", "Jones", 25));
    log.info("Natural: {}", people.stream().sorted().map(Person::getFullName).toList());
    log.info("By age: {}", people.stream().sorted(Comparator.comparing(Person::age)).map(p -> p.getFullName() + "(" + p.age() + ")").toList());
  }

  /**
   * Demonstrates takeWhile and dropWhile on ordered streams.
   */
  public static void takeWhileDropWhileExample() {
    log.info("=== takeWhile/dropWhile ===");
    List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7);
    log.info("takeWhile <4: {}", numbers.stream().takeWhile(n -> n < 4).toList());
    log.info("dropWhile <4: {}", numbers.stream().dropWhile(n -> n < 4).toList());
  }

  /**
   * Simple runner for the Stream examples.
   */
  public static void main(String[] args) {
    streamCreationExample();
    filterExample();
    mapExample();
    flatMapExample();
    distinctSortedLimitExample();
    peekExample();
    sortingExample();
    takeWhileDropWhileExample();
  }
}
