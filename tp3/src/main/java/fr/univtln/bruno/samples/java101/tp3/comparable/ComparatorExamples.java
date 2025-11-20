package fr.univtln.bruno.samples.java101.tp3.comparable;

import fr.univtln.bruno.samples.java101.tp3.Book;
import fr.univtln.bruno.samples.java101.tp3.Person;
import fr.univtln.bruno.samples.java101.tp3.functional.MappingAndSortingExamples;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.*;

/**
 * Examples illustrating Comparable/Comparator usage and common comparator idioms.
 *
 * <p>Note pédagogique : certains exemples utilisent volontairement des classes anonymes
 * au lieu de lambdas ou de method-references. L'objectif est didactique :
 * - montrer la structure complète d'un Comparator (méthode compare) pour les étudiants
 *   qui découvrent l'interface et la logique de comparaison ;
 * - permettre de discuter des différences entre les approches (verbosité vs concision).
 *
 * Chaque méthode comporte une alternative moderne (lambda / Comparator.comparing)
 * sous forme de commentaire pour que l'enseignant puisse basculer facilement vers la
 * version idiomatique une fois que les élèves ont compris les concepts.
 */
@Slf4j
public class ComparatorExamples {
  /**
   * Demonstrate natural ordering via Comparable implementation.
   */
  public static void naturalOrderingExample() {
    log.info("=== Natural Ordering ===");
    List<Person> people = new ArrayList<>(List.of(
      Person.of("Charlie", "Brown", 35),
      Person.of("Alice", "Smith", 30),
      Person.of("Bob", "Jones", 25),
      Person.of("Alice", "Brown", 28)
    ));
    log.debug("Before natural sort: {}", people);
    Collections.sort(people);
    log.debug("After natural sort: {}", people);
    MappingAndSortingExamples.printFullNames(people);
  }

  /**
   * Show simple comparators based on attributes.
   */
  public static void customComparatorExample() {
    log.info("=== Custom Comparator ===");
    List<Person> people = new ArrayList<>(List.of(
      Person.of("Charlie", "Brown", 35),
      Person.of("Alice", "Smith", 30),
      Person.of("Bob", "Jones", 25)
    ));
    log.debug("Before age sort: {}", people);
    // Use an anonymous Comparator instead of a method reference
    // (didactic) - shows the full compare method implementation.
    people.sort(new Comparator<Person>() {
      @Override
      public int compare(Person a, Person b) {
        return Integer.compare(a.age(), b.age());
      }
    });
    log.debug("After age asc sort: {}", people);
    log.info("By age asc: {}", people.stream().map(p -> p.getFullName() + "(" + p.age() + ")").toList());

    // Modern, idiomatic equivalent (commented for reference):
    // people.sort(Comparator.comparingInt(Person::age));

    // reversed using an anonymous Comparator as well
    people.sort(new Comparator<Person>() {
      @Override
      public int compare(Person a, Person b) {
        return Integer.compare(a.age(), b.age());
      }
    }.reversed());
    log.debug("After age desc sort: {}", people);
    log.info("By age desc: {}", people.stream().map(p -> p.getFullName() + "(" + p.age() + ")").toList());

    // Modern reversed example (commented):
    // people.sort(Comparator.comparingInt(Person::age).reversed());

    // compare by firstName with anonymous Comparator (avoid method reference)
    people.sort(new Comparator<Person>() {
      @Override
      public int compare(Person a, Person b) {
        return a.firstName().compareTo(b.firstName());
      }
    });
    log.debug("After firstName sort: {}", people);
    log.info("By firstName: {}", people.stream().map(Person::getFullName).toList());

    // Modern equivalent (commented):
    // people.sort(Comparator.comparing(Person::firstName));
  }

  /**
   * Chaining comparators to implement multi-key sorting.
   */
  public static void comparatorChainingExample() {
    log.info("=== Comparator Chaining ===");
    List<Person> people = new ArrayList<>(List.of(
      Person.of("Alice", "Smith", 30),
      Person.of("Bob", "Smith", 25),
      Person.of("Alice", "Jones", 30),
      Person.of("Charlie", "Brown", 35)
    ));
    log.debug("Before chaining sort: {}", people);
    // Build chained comparator using anonymous classes to avoid method references
    // (didactic). This shows how each comparator contributes to the final ordering.
    Comparator<Person> lastNameCmp = new Comparator<>() {
      @Override
      public int compare(Person a, Person b) {
        return a.lastName().compareTo(b.lastName());
      }
    };
    Comparator<Person> firstNameCmp = new Comparator<>() {
      @Override
      public int compare(Person a, Person b) {
        return a.firstName().compareTo(b.firstName());
      }
    };
    Comparator<Person> ageCmp = new Comparator<>() {
      @Override
      public int compare(Person a, Person b) {
        return Integer.compare(a.age(), b.age());
      }
    };
    people.sort(lastNameCmp.thenComparing(firstNameCmp).thenComparing(ageCmp));
    log.debug("After chaining sort: {}", people);
    log.info("Chained: {}", people.stream().map(p -> p.getFullName() + "(" + p.age() + ")").toList());

    // Modern, concise alternative (commented):
    // people.sort(Comparator.comparing(Person::lastName)
    //                      .thenComparing(Person::firstName)
    //                      .thenComparingInt(Person::age));
  }

  /**
   * Show Comparator factory methods such as naturalOrder, reverseOrder, nullsFirst/Last.
   */
  public static void comparatorFactoryMethodsExample() {
    log.info("=== Comparator Factory Methods ===");
    List<String> words = new ArrayList<>(List.of("banana", "Apple", "cherry", "DATE"));
    List<String> natural = new ArrayList<>(words);
    natural.sort(Comparator.naturalOrder());
    List<String> reversed = new ArrayList<>(words);
    reversed.sort(Comparator.reverseOrder());
    List<String> ci = new ArrayList<>(words);
    ci.sort(String.CASE_INSENSITIVE_ORDER);
    // sort by length using anonymous Comparator (avoid method reference)
    List<String> byLen = new ArrayList<>(words);
    byLen.sort(new Comparator<String>() {
      @Override
      public int compare(String a, String b) {
        return Integer.compare(a.length(), b.length());
      }
    });
    // sort by length then case-insensitive alphabetical order using anonymous comparator
    List<String> lenThenAlpha = new ArrayList<>(words);
    lenThenAlpha.sort(new Comparator<String>() {
      @Override
      public int compare(String a, String b) {
        int c = Integer.compare(a.length(), b.length());
        if (c != 0) return c;
        return String.CASE_INSENSITIVE_ORDER.compare(a, b);
      }
    });
    log.info("Natural {}", natural);
    log.info("Reverse {}", reversed);
    log.info("CaseInsensitive {}", ci);
    log.info("Length {}", byLen);
    log.info("Len+Alpha {}", lenThenAlpha);

    // Student note: avoid writing comparators that are not transitive. A non-transitive comparator
    // can produce A<B, B<C but A>C, which breaks sorting algorithms and collections relying on total order.
    // Example of a bad comparator (do NOT use in real code):
    // Comparator<String> bad = (a, b) -> {
    //   if (a.length() % 2 == 0 && b.length() % 2 == 1) return -1;
    //   if (a.length() % 2 == 1 && b.length() % 2 == 0) return 1;
    //   return a.compareTo(b);
    // };
  }

  /**
   * Null-handling comparators example.
   */
  public static void nullHandlingExample() {
    log.info("=== Null Handling Comparators ===");
    List<String> withNulls = new ArrayList<>(Arrays.asList("Charlie", null, "Alice", "Bob", null));
    List<String> nullsFirst = new ArrayList<>(withNulls);
    nullsFirst.sort(Comparator.nullsFirst(Comparator.naturalOrder()));
    List<String> nullsLast = new ArrayList<>(withNulls);
    nullsLast.sort(Comparator.nullsLast(Comparator.naturalOrder()));
    log.info("NullsFirst {}", nullsFirst);
    log.info("NullsLast {}", nullsLast);
  }

  /**
   * Complex sorting example combining multiple keys on Book objects.
   */
  public static void complexSortingExample() {
    log.info("=== Complex Sorting Books ===");
    List<Book> books = new ArrayList<>(List.of(
      Book.of("978-0134685991", "Effective Java", "Joshua Bloch", LocalDate.of(2018, 1, 6), 45.99),
      Book.of("978-0596009205", "Head First Java", "Kathy Sierra", LocalDate.of(2005, 2, 9), 39.99),
      Book.of("978-0134685992", "Java Concurrency", "Brian Goetz", LocalDate.of(2006, 5, 19), 42.99),
      Book.of("978-0321356680", "Clean Code", "Robert Martin", LocalDate.of(2008, 5, 28), 38.99),
      Book.of("978-0134685993", "Modern Java", "Joshua Bloch", LocalDate.of(2020, 3, 15), 49.99)
    ));
    log.debug("Books initial: {}", books);
    // Use anonymous comparators to avoid method references (didactic). Students can
    // compare this explicit form with Comparator.comparing and thenComparing.
    List<Book> byPrice = new ArrayList<>(books);
    byPrice.sort(new Comparator<Book>() {
      @Override
      public int compare(Book a, Book b) {
        return Double.compare(a.price(), b.price());
      }
    });

    List<Book> byDateDesc = new ArrayList<>(books);
    byDateDesc.sort(new Comparator<Book>() {
      @Override
      public int compare(Book a, Book b) {
        return a.publishedDate().compareTo(b.publishedDate());
      }
    }.reversed());

    List<Book> byAuthorPrice = new ArrayList<>(books);
    byAuthorPrice.sort(new Comparator<Book>() {
      @Override
      public int compare(Book a, Book b) {
        int c = a.author().compareTo(b.author());
        if (c != 0) return c;
        return Double.compare(a.price(), b.price());
      }
    });
    log.debug("byPrice: {} byDateDesc: {} byAuthorPrice: {}", byPrice, byDateDesc, byAuthorPrice);
    MappingAndSortingExamples.printBookDescriptions(byPrice);
    MappingAndSortingExamples.printBookDescriptions(byDateDesc);
    MappingAndSortingExamples.printBookDescriptions(byAuthorPrice);

    // Modern equivalents (commented):
    // byPrice.sort(Comparator.comparingDouble(Book::price));
    // byDateDesc.sort(Comparator.comparing(Book::publishedDate).reversed());
    // byAuthorPrice.sort(Comparator.comparing(Book::author).thenComparingDouble(Book::price));
  }

  /**
   * Min/Max examples using Comparators.
   */
  public static void minMaxExample() {
    log.info("=== Min/Max Example ===");
    List<Person> people = List.of(
      Person.of("Charlie", "Brown", 35),
      Person.of("Alice", "Smith", 30),
      Person.of("Bob", "Jones", 25)
    );
    // use anonymous comparator for age
    Comparator<Person> ageComparator = new Comparator<>() {
      @Override
      public int compare(Person a, Person b) {
        return Integer.compare(a.age(), b.age());
      }
    };
    Person youngest = Collections.min(people, ageComparator);
    Person oldest = Collections.max(people, ageComparator);
    log.info("Youngest {}({}) Oldest {}({})", youngest.getFullName(), youngest.age(), oldest.getFullName(), oldest.age());
    MappingAndSortingExamples.showMinMaxByAge(people);
  }

  /**
   * Examples of custom comparator implementations (anonymous, lambda, method reference).
   */
  public static void customComparatorImplementationExample() {
    log.info("=== Custom Implementation ===");
    Comparator<Person> anonymous = new Comparator<>() {
      public int compare(Person a, Person b) {
        return Integer.compare(a.age(), b.age());
      }
    };
    Comparator<Person> lambda = (a, b) -> Integer.compare(a.age(), b.age());
    // replace method reference comparator with anonymous comparator
    Comparator<Person> methodRef = new Comparator<>() {
      @Override
      public int compare(Person a, Person b) {
        return Integer.compare(a.age(), b.age());
      }
    };
    List<Person> people = new ArrayList<>(List.of(Person.of("Charlie", "Brown", 35), Person.of("Alice", "Smith", 30), Person.of("Bob", "Jones", 25)));
    people.sort(methodRef);
    MappingAndSortingExamples.printNamesWithAges(people);
    people.sort(anonymous.reversed());
    MappingAndSortingExamples.printNamesWithAges(people);
  }

  /**
   * Runner for comparator examples.
   *
   * @param args ignored
   */
  public static void main(String[] args) {
    naturalOrderingExample();
    customComparatorExample();
    comparatorChainingExample();
    comparatorFactoryMethodsExample();
    nullHandlingExample();
    complexSortingExample();
    minMaxExample();
    customComparatorImplementationExample();
  }
}
