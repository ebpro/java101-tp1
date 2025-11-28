package fr.univtln.bruno.samples.java101.tp4.lambda;

import fr.univtln.bruno.samples.java101.tp4.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Demonstrates method references in various forms.
 *
 * <p>Method reference types:
 * 1. Static method reference: ClassName::staticMethod
 * 2. Instance method reference on object: instance::instanceMethod
 * 3. Instance method reference on type: ClassName::instanceMethod
 * 4. Constructor reference: ClassName::new
 * </p>
 */
@Slf4j
public class MethodReferenceExamples {

  private MethodReferenceExamples() {
    // Utility class
  }

  /**
   * Demonstrates static method references.
   */
  public static void staticMethodReferences() {
    log.info("=== Static Method References ===");

    List<String> numbers = List.of("42", "123", "7", "999");

    // Lambda version
    List<Integer> parsed1 = numbers.stream()
        .map(s -> Integer.parseInt(s))
        .toList();

    // Method reference version (more concise)
    List<Integer> parsed2 = numbers.stream()
        .map(Integer::parseInt)
        .toList();

    log.info("Parsed numbers: {}", parsed2);

    // Another example with Math.sqrt
    List<Double> values = List.of(4.0, 9.0, 16.0, 25.0);
    List<Double> squareRoots = values.stream()
        .map(Math::sqrt)
        .toList();

    log.info("Square roots: {}", squareRoots);
  }

  /**
   * Demonstrates instance method references on a specific object.
   */
  public static void instanceMethodReferences() {
    log.info("=== Instance Method References (Bound) ===");

    List<String> words = List.of("hello", "world", "java", "functional");

    String prefix = ">> ";

    // Lambda: call instance method on captured object
    List<String> prefixed1 = words.stream()
        .map(w -> prefix.concat(w))
        .toList();

    // Method reference on instance (bound receiver)
    List<String> prefixed2 = words.stream()
        .map(prefix::concat)
        .toList();

    log.info("Prefixed: {}", prefixed2);
  }

  /**
   * Demonstrates instance method references on a type (unbound).
   */
  public static void unboundMethodReferences() {
    log.info("=== Instance Method References (Unbound) ===");

    List<String> words = List.of("Hello", "World", "Java");

    // Lambda: call instance method on parameter
    List<String> lowercase1 = words.stream()
        .map(s -> s.toLowerCase())
        .toList();

    // Method reference on type (unbound receiver)
    // The stream element becomes the receiver
    List<String> lowercase2 = words.stream()
        .map(String::toLowerCase)
        .toList();

    log.info("Lowercase: {}", lowercase2);

    // More complex example with Person
    List<Person> people = List.of(
        new Person("Alice", 25),
        new Person("Bob", 30),
        new Person("Charlie", 17)
    );

    // Extract names using unbound method reference
    List<String> names = people.stream()
        .map(Person::name) // equivalent to p -> p.name()
        .toList();

    log.info("Names: {}", names);
  }

  /**
   * Demonstrates constructor references.
   */
  public static void constructorReferences() {
    log.info("=== Constructor References ===");

    // Constructor with parameters using BiFunction
    BiFunction<String, Integer, Person> personCreator = Person::new;
    Person person = personCreator.apply("Alice", 25);
    log.info("Created person: {}", person);

    // Supplier with lambda (Person has no no-arg constructor)
    Supplier<Person> personFactory = () -> new Person("Unknown", 0);
    Person defaultPerson = personFactory.get();
    log.info("Default person: {}", defaultPerson);

    // Using constructor reference in stream
    record PersonData(String name, int age) {}

    List<PersonData> data = List.of(
        new PersonData("Alice", 25),
        new PersonData("Bob", 30),
        new PersonData("Charlie", 17)
    );

    // Convert PersonData to Person using constructor reference
    List<Person> people = data.stream()
        .map(d -> new Person(d.name(), d.age()))
        .toList();

    log.info("Converted people: {}", people);
  }

  /**
   * Demonstrates array constructor references.
   */
  public static void arrayConstructorReferences() {
    log.info("=== Array Constructor References ===");

    List<String> words = List.of("one", "two", "three", "four");

    // Lambda to create array
    String[] array1 = words.stream()
        .toArray(size -> new String[size]);

    // Array constructor reference
    String[] array2 = words.stream()
        .toArray(String[]::new);

    log.info("Array length: {}", array2.length);
    log.info("Array content: {}", String.join(", ", array2));
  }

  /**
   * Demonstrates choosing between lambda and method reference.
   */
  public static void whenToUseMethodReferences() {
    log.info("=== When to Use Method References ===");

    List<Person> people = List.of(
        new Person("Alice", 25),
        new Person("Bob", 30),
        new Person("Charlie", 17)
    );

    // Use method reference when it's a direct delegation
    people.stream()
        .map(Person::name)  // Good: direct accessor
        .forEach(log::info); // Good: direct delegation

    // Use lambda when you need transformation or multiple parameters
    people.stream()
        .map(p -> p.name().toUpperCase()) // Lambda needed for chaining
        .forEach(name -> log.info("Name: {}", name)); // Lambda needed for formatting

    // Use lambda when logic is complex
    Function<Person, String> complexTransform = p -> {
      if (p.isAdult()) {
        return "Adult: " + p.name();
      } else {
        return "Minor: " + p.name();
      }
    };

    people.stream()
        .map(complexTransform)
        .forEach(log::info);
  }
}

