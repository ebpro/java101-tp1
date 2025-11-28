package fr.univtln.bruno.samples.java101.tp4.functionalinterfaces;

import fr.univtln.bruno.samples.java101.tp4.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.*;

/**
 * Demonstrates standard functional interfaces from java.util.function package.
 *
 * <p>Main functional interfaces:
 * - Predicate&lt;T&gt;: T -> boolean (test/filter)
 * - Function&lt;T,R&gt;: T -> R (transform)
 * - Consumer&lt;T&gt;: T -> void (side effect)
 * - Supplier&lt;T&gt;: () -> T (provide/create)
 * - UnaryOperator&lt;T&gt;: T -> T (special Function)
 * - BinaryOperator&lt;T&gt;: (T,T) -> T (special BiFunction)
 * </p>
 */
@Slf4j
public class StandardFunctionalInterfacesExamples {

  private StandardFunctionalInterfacesExamples() {
    // Utility class
  }

  /**
   * Demonstrates Predicate&lt;T&gt; for testing/filtering.
   */
  public static void predicateExamples() {
    log.info("=== Predicate Examples ===");

    List<Person> people = List.of(
        new Person("Alice", 25),
        new Person("Bob", 30),
        new Person("Charlie", 17),
        new Person("David", 22)
    );

    // Predicate: test a condition
    Predicate<Person> isAdult = p -> p.age() >= 18;
    Predicate<Person> isYoung = p -> p.age() < 30;

    log.info("Adults:");
    people.stream()
        .filter(isAdult)
        .forEach(p -> log.info("  {}", p.name()));

    // Combining predicates
    Predicate<Person> isYoungAdult = isAdult.and(isYoung);

    log.info("Young adults (18-29):");
    people.stream()
        .filter(isYoungAdult)
        .forEach(p -> log.info("  {}", p.name()));

    // BiPredicate: test with two arguments
    BiPredicate<Person, Integer> isOlderThan = (person, age) -> person.age() > age;

    log.info("People older than 20:");
    people.stream()
        .filter(p -> isOlderThan.test(p, 20))
        .forEach(p -> log.info("  {}", p.name()));
  }

  /**
   * Demonstrates Function&lt;T,R&gt; for transformations.
   */
  public static void functionExamples() {
    log.info("=== Function Examples ===");

    List<Person> people = List.of(
        new Person("Alice", 25),
        new Person("Bob", 30)
    );

    // Function: transform T to R
    Function<Person, String> toGreeting = p -> "Hello, " + p.name() + "!";
    Function<Person, Integer> getAge = Person::age;

    log.info("Greetings:");
    people.stream()
        .map(toGreeting)
        .forEach(log::info);

    // Function composition
    Function<String, String> upperCase = String::toUpperCase;
    Function<Person, String> toUpperGreeting = toGreeting.andThen(upperCase);

    log.info("Upper case greetings:");
    people.stream()
        .map(toUpperGreeting)
        .forEach(log::info);

    // BiFunction: transform (T, U) to R
    BiFunction<String, Integer, Person> createPerson = Person::new;
    Person newPerson = createPerson.apply("Eve", 28);
    log.info("Created: {}", newPerson);
  }

  /**
   * Demonstrates Consumer&lt;T&gt; for side effects.
   */
  public static void consumerExamples() {
    log.info("=== Consumer Examples ===");

    List<Person> people = List.of(
        new Person("Alice", 25),
        new Person("Bob", 30),
        new Person("Charlie", 17)
    );

    // Consumer: perform action on T
    Consumer<Person> logPerson = p -> log.info("Person: {} ({})", p.name(), p.age());
    Consumer<Person> logAge = p -> log.info("Age: {}", p.age());

    people.forEach(logPerson);

    // Chaining consumers
    Consumer<Person> logBoth = logPerson.andThen(logAge);

    log.info("Chained consumers:");
    people.stream()
        .filter(Person::isAdult)
        .forEach(logBoth);

    // BiConsumer: perform action on (T, U)
    BiConsumer<String, Integer> logNameAge = (name, age) ->
        log.info("{} is {} years old", name, age);

    people.forEach(p -> logNameAge.accept(p.name(), p.age()));
  }

  /**
   * Demonstrates Supplier&lt;T&gt; for providing values.
   */
  public static void supplierExamples() {
    log.info("=== Supplier Examples ===");

    // Supplier: provide T (lazy evaluation)
    Supplier<Person> defaultPersonSupplier = () -> new Person("Unknown", 0);
    Supplier<Long> timestampSupplier = System::currentTimeMillis;

    Person defaultPerson = defaultPersonSupplier.get();
    log.info("Default person: {}", defaultPerson);

    long timestamp1 = timestampSupplier.get();
    log.info("Timestamp: {}", timestamp1);

    // Lazy evaluation use case - supplier is only evaluated when get() is called
    Supplier<String> expensiveOperation = () -> {
      log.info("Performing heavy computation...");
      return "Expensive Result";
    };

    // Simulate conditional evaluation - only compute if needed
    String result;
    if (Math.random() > 0.5) {
      result = expensiveOperation.get(); // Only evaluated here if condition is true
    } else {
      result = "Skipped computation";
    }
    log.info("Lazy result: {}", result);
  }

  /**
   * Demonstrates UnaryOperator and BinaryOperator.
   */
  public static void operatorExamples() {
    log.info("=== Operator Examples ===");

    // UnaryOperator<T>: T -> T (special case of Function<T,T>)
    UnaryOperator<String> toUpper = String::toUpperCase;
    UnaryOperator<Integer> square = x -> x * x;

    log.info("Upper: {}", toUpper.apply("hello"));
    log.info("Square of 5: {}", square.apply(5));

    // BinaryOperator<T>: (T,T) -> T (special case of BiFunction<T,T,T>)
    BinaryOperator<Integer> sum = (a, b) -> a + b;
    BinaryOperator<Integer> max = Integer::max;
    BinaryOperator<String> concat = (s1, s2) -> s1 + s2;

    log.info("Sum: {}", sum.apply(10, 20));
    log.info("Max: {}", max.apply(10, 20));
    log.info("Concat: {}", concat.apply("Hello", "World"));

    // Use in reduce operations
    List<Integer> numbers = List.of(1, 2, 3, 4, 5);
    int total = numbers.stream().reduce(0, sum);
    log.info("Total: {}", total);
  }

  /**
   * Demonstrates primitive specializations to avoid boxing.
   */
  public static void primitiveSpecializations() {
    log.info("=== Primitive Specializations ===");

    // IntPredicate instead of Predicate<Integer>
    IntPredicate isEven = n -> n % 2 == 0;
    log.info("Is 4 even? {}", isEven.test(4));

    // IntFunction<R> instead of Function<Integer, R>
    IntFunction<String> intToString = n -> "Number: " + n;
    log.info(intToString.apply(42));

    // ToIntFunction<T> instead of Function<T, Integer>
    ToIntFunction<String> stringLength = String::length;
    log.info("Length of 'hello': {}", stringLength.applyAsInt("hello"));

    // IntUnaryOperator instead of UnaryOperator<Integer>
    IntUnaryOperator doubleIt = n -> n * 2;
    log.info("Double of 5: {}", doubleIt.applyAsInt(5));

    // IntBinaryOperator instead of BinaryOperator<Integer>
    IntBinaryOperator multiply = (a, b) -> a * b;
    log.info("Product: {}", multiply.applyAsInt(6, 7));

    // Similar for Long and Double: LongPredicate, DoublePredicate, etc.
  }
}

