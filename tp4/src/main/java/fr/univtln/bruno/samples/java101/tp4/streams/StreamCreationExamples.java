package fr.univtln.bruno.samples.java101.tp4.streams;

import fr.univtln.bruno.samples.java101.tp4.Person;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Demonstrates various ways to create streams.
 *
 * <p>Stream creation methods:
 * - From collections
 * - From arrays
 * - Stream builders
 * - Generate and iterate
 * - From files and I/O
 * - Primitive streams
 * </p>
 */
@Slf4j
public class StreamCreationExamples {

  private StreamCreationExamples() {
    // Utility class
  }

  /**
   * Demonstrates creating streams from collections.
   */
  public static void streamsFromCollections() {
    log.info("=== Streams from Collections ===");

    List<String> list = List.of("apple", "banana", "cherry");
    Set<String> set = Set.of("red", "green", "blue");

    // Stream from List
    log.info("From list:");
    list.stream()
        .map(String::toUpperCase)
        .forEach(log::info);

    // Stream from Set
    log.info("From set:");
    set.stream()
        .sorted()
        .forEach(log::info);

    // Parallel stream
    log.info("Parallel stream:");
    list.parallelStream()
        .map(String::toUpperCase)
        .forEach(s -> log.info("Thread: {}, Value: {}",
            Thread.currentThread().getName(), s));
  }

  /**
   * Demonstrates creating streams from arrays.
   */
  public static void streamsFromArrays() {
    log.info("=== Streams from Arrays ===");

    String[] array = {"one", "two", "three"};

    // Using Arrays.stream()
    Arrays.stream(array)
        .forEach(log::info);

    // Using Stream.of()
    Stream.of("four", "five", "six")
        .forEach(log::info);

    // From primitive arrays
    int[] numbers = {1, 2, 3, 4, 5};
    int sum = Arrays.stream(numbers).sum();
    log.info("Sum: {}", sum);

    // Partial array stream (range)
    log.info("Partial array (1-3):");
    Arrays.stream(array, 1, 3) // indices 1 and 2
        .forEach(log::info);
  }

  /**
   * Demonstrates Stream.builder() for constructing streams.
   */
  public static void streamBuilder() {
    log.info("=== Stream Builder ===");

    Stream<Person> personStream = Stream.<Person>builder()
        .add(new Person("Alice", 25))
        .add(new Person("Bob", 30))
        .add(new Person("Charlie", 17))
        .build();

    personStream.forEach(p -> log.info("{}", p));
  }

  /**
   * Demonstrates Stream.generate() for infinite streams.
   */
  public static void streamGenerate() {
    log.info("=== Stream.generate() ===");

    // Generate infinite stream of random numbers
    Stream.generate(Math::random)
        .limit(5)
        .forEach(n -> log.info("Random: {}", n));

    // Generate with supplier
    Stream.generate(() -> "constant")
        .limit(3)
        .forEach(log::info);

    // Generate with state (needs to be effectively final)
    var counter = new Object() { int value = 0; };
    Stream.generate(() -> "Item-" + (++counter.value))
        .limit(5)
        .forEach(log::info);
  }

  /**
   * Demonstrates Stream.iterate() for sequences.
   */
  public static void streamIterate() {
    log.info("=== Stream.iterate() ===");

    // Infinite sequence: 0, 2, 4, 6, 8, ...
    Stream.iterate(0, n -> n + 2)
        .limit(5)
        .forEach(n -> log.info("Even: {}", n));

    // With predicate (Java 9+): stops when predicate is false
    log.info("Numbers less than 20:");
    Stream.iterate(1, n -> n < 20, n -> n * 2)
        .forEach(n -> log.info("Power of 2: {}", n));

    // Fibonacci sequence
    log.info("Fibonacci:");
    Stream.iterate(new int[]{0, 1}, f -> new int[]{f[1], f[0] + f[1]})
        .limit(10)
        .map(f -> f[0])
        .forEach(n -> log.info("{}", n));
  }

  /**
   * Demonstrates primitive streams (IntStream, LongStream, DoubleStream).
   */
  public static void primitiveStreams() {
    log.info("=== Primitive Streams ===");

    // IntStream range (exclusive end)
    log.info("Range 1-5:");
    IntStream.range(1, 6)
        .forEach(n -> log.info("{}", n));

    // IntStream rangeClosed (inclusive end)
    log.info("Range closed 1-5:");
    IntStream.rangeClosed(1, 5)
        .forEach(n -> log.info("{}", n));

    // Useful for loops replacement
    log.info("Index-based iteration:");
    List<String> items = List.of("A", "B", "C", "D");
    IntStream.range(0, items.size())
        .forEach(i -> log.info("[{}] = {}", i, items.get(i)));

    // Statistical operations on primitive streams
    IntStream numbers = IntStream.of(1, 2, 3, 4, 5);
    IntSummaryStatistics stats = numbers.summaryStatistics();
    log.info("Stats: count={}, sum={}, min={}, max={}, average={}",
        stats.getCount(), stats.getSum(), stats.getMin(),
        stats.getMax(), stats.getAverage());
  }

  /**
   * Demonstrates creating streams from strings.
   */
  public static void streamsFromStrings() {
    log.info("=== Streams from Strings ===");

    String text = "Hello World";

    // Stream of characters (as IntStream)
    text.chars()
        .mapToObj(c -> (char) c)
        .forEach(c -> log.info("Char: {}", c));

    // Stream of code points
    text.codePoints()
        .mapToObj(Character::toString)
        .forEach(s -> log.info("Code point: {}", s));

    // Split and stream
    "one,two,three,four".lines(); // For multi-line strings

    Arrays.stream("one two three four".split(" "))
        .forEach(log::info);
  }

  /**
   * Demonstrates empty and singleton streams.
   */
  public static void emptyAndSingletonStreams() {
    log.info("=== Empty and Singleton Streams ===");

    // Empty stream
    Stream<String> empty = Stream.empty();
    log.info("Empty stream count: {}", empty.count());

    // Singleton stream
    Stream.of("single").forEach(log::info);

    // ofNullable (Java 9+): stream with 0 or 1 element
    String nullable = Math.random() > 0.5 ? "value" : null;
    Stream.ofNullable(nullable)
        .forEach(s -> log.info("Nullable value: {}", s));
  }

  /**
   * Demonstrates concatenating streams.
   */
  public static void concatenatingStreams() {
    log.info("=== Concatenating Streams ===");

    Stream<String> stream1 = Stream.of("A", "B", "C");
    Stream<String> stream2 = Stream.of("D", "E", "F");

    Stream<String> combined = Stream.concat(stream1, stream2);
    combined.forEach(log::info);

    // Multiple concatenations
    Stream<Integer> concat = Stream.concat(
        Stream.concat(
            Stream.of(1, 2, 3),
            Stream.of(4, 5, 6)
        ),
        Stream.of(7, 8, 9)
    );

    log.info("Concatenated sum: {}", concat.reduce(0, Integer::sum));
  }

  /**
   * Demonstrates creating streams from Optional.
   */
  public static void streamsFromOptional() {
    log.info("=== Streams from Optional ===");

    Optional<String> optional1 = Optional.of("Present");
    Optional<String> optional2 = Optional.empty();

    // Convert Optional to Stream (Java 9+)
    log.info("From present Optional:");
    optional1.stream().forEach(log::info);

    log.info("From empty Optional:");
    optional2.stream().forEach(log::info);

    // Useful for flatMapping optionals
    List<Optional<String>> optionals = List.of(
        Optional.of("A"),
        Optional.empty(),
        Optional.of("B"),
        Optional.empty(),
        Optional.of("C")
    );

    log.info("Flattened optionals:");
    optionals.stream()
        .flatMap(Optional::stream)
        .forEach(log::info);
  }
}

