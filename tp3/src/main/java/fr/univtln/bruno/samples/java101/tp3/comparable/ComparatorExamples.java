package fr.univtln.bruno.samples.java101.tp3.comparable;

import fr.univtln.bruno.samples.java101.tp3.Book;
import fr.univtln.bruno.samples.java101.tp3.Person;
import fr.univtln.bruno.samples.java101.tp3.functionnal.MappingAndSortingExamples;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.*;

/**
 * Examples illustrating Comparable/Comparator usage and common comparator idioms.
 */
@Slf4j
public class ComparatorExamples {
    /** Demonstrate natural ordering via Comparable implementation. */
    public static void naturalOrderingExample() {
        log.info("=== Natural Ordering ===");
        List<Person> people = new ArrayList<>(List.of(
                Person.of("Charlie","Brown",35),
                Person.of("Alice","Smith",30),
                Person.of("Bob","Jones",25),
                Person.of("Alice","Brown",28)
        ));
        Collections.sort(people);
        MappingAndSortingExamples.printFullNames(people);
    }
    /** Show simple comparators based on attributes. */
    public static void customComparatorExample() {
        log.info("=== Custom Comparator ===");
        List<Person> people = new ArrayList<>(List.of(
                Person.of("Charlie","Brown",35),
                Person.of("Alice","Smith",30),
                Person.of("Bob","Jones",25)
        ));
        people.sort(Comparator.comparing(Person::getAge));
        log.info("By age asc: {}", people.stream().map(p->p.getFullName()+"("+p.getAge()+")").toList());
        people.sort(Comparator.comparing(Person::getAge).reversed());
        log.info("By age desc: {}", people.stream().map(p->p.getFullName()+"("+p.getAge()+")").toList());
        people.sort(Comparator.comparing(Person::getFirstName));
        log.info("By firstName: {}", people.stream().map(Person::getFullName).toList());
    }
    /** Chaining comparators to implement multi-key sorting. */
    public static void comparatorChainingExample() {
        log.info("=== Comparator Chaining ===");
        List<Person> people = new ArrayList<>(List.of(
                Person.of("Alice","Smith",30),
                Person.of("Bob","Smith",25),
                Person.of("Alice","Jones",30),
                Person.of("Charlie","Brown",35)
        ));
        people.sort(Comparator.comparing(Person::getLastName)
                .thenComparing(Person::getFirstName)
                .thenComparing(Person::getAge));
        log.info("Chained: {}", people.stream().map(p->p.getFullName()+"("+p.getAge()+")").toList());
    }
    /** Show Comparator factory methods such as naturalOrder, reverseOrder, nullsFirst/Last. */
    public static void comparatorFactoryMethodsExample() {
        log.info("=== Comparator Factory Methods ===");
        List<String> words = new ArrayList<>(List.of("banana","Apple","cherry","DATE"));
        List<String> natural = new ArrayList<>(words); natural.sort(Comparator.naturalOrder());
        List<String> reversed = new ArrayList<>(words); reversed.sort(Comparator.reverseOrder());
        List<String> ci = new ArrayList<>(words); ci.sort(String.CASE_INSENSITIVE_ORDER);
        List<String> byLen = new ArrayList<>(words); byLen.sort(Comparator.comparing(String::length));
        List<String> lenThenAlpha = new ArrayList<>(words); lenThenAlpha.sort(Comparator.comparing(String::length).thenComparing(String.CASE_INSENSITIVE_ORDER));
        log.info("Natural {}", natural);
        log.info("Reverse {}", reversed);
        log.info("CaseInsensitive {}", ci);
        log.info("Length {}", byLen);
        log.info("Len+Alpha {}", lenThenAlpha);
    }
    /** Null-handling comparators example. */
    public static void nullHandlingExample() {
        log.info("=== Null Handling Comparators ===");
        List<String> withNulls = new ArrayList<>(Arrays.asList("Charlie", null, "Alice", "Bob", null));
        List<String> nullsFirst = new ArrayList<>(withNulls); nullsFirst.sort(Comparator.nullsFirst(Comparator.naturalOrder()));
        List<String> nullsLast = new ArrayList<>(withNulls); nullsLast.sort(Comparator.nullsLast(Comparator.naturalOrder()));
        log.info("NullsFirst {}", nullsFirst); log.info("NullsLast {}", nullsLast);
    }
    /** Complex sorting example combining multiple keys on Book objects. */
    public static void complexSortingExample() {
        log.info("=== Complex Sorting Books ===");
        List<Book> books = new ArrayList<>(List.of(
                Book.of("978-0134685991","Effective Java","Joshua Bloch", LocalDate.of(2018,1,6),45.99),
                Book.of("978-0596009205","Head First Java","Kathy Sierra", LocalDate.of(2005,2,9),39.99),
                Book.of("978-0134685992","Java Concurrency","Brian Goetz", LocalDate.of(2006,5,19),42.99),
                Book.of("978-0321356680","Clean Code","Robert Martin", LocalDate.of(2008,5,28),38.99),
                Book.of("978-0134685993","Modern Java","Joshua Bloch", LocalDate.of(2020,3,15),49.99)
        ));
        List<Book> byPrice = new ArrayList<>(books); byPrice.sort(Comparator.comparing(Book::getPrice));
        List<Book> byDateDesc = new ArrayList<>(books); byDateDesc.sort(Comparator.comparing(Book::getPublishedDate).reversed());
        List<Book> byAuthorPrice = new ArrayList<>(books); byAuthorPrice.sort(Comparator.comparing(Book::getAuthor).thenComparing(Book::getPrice));
        MappingAndSortingExamples.printBookDescriptions(byPrice);
        MappingAndSortingExamples.printBookDescriptions(byDateDesc);
        MappingAndSortingExamples.printBookDescriptions(byAuthorPrice);
    }
    /** Min/Max examples using Comparators. */
    public static void minMaxExample() {
        log.info("=== Min/Max Example ===");
        List<Person> people = List.of(
                Person.of("Charlie","Brown",35),
                Person.of("Alice","Smith",30),
                Person.of("Bob","Jones",25)
        );
        Person youngest = Collections.min(people, Comparator.comparing(Person::getAge));
        Person oldest = Collections.max(people, Comparator.comparing(Person::getAge));
        log.info("Youngest {}({}) Oldest {}({})", youngest.getFullName(), youngest.getAge(), oldest.getFullName(), oldest.getAge());
        MappingAndSortingExamples.showMinMaxByAge(people);
    }
    /** Examples of custom comparator implementations (anonymous, lambda, method reference). */
    public static void customComparatorImplementationExample() {
        log.info("=== Custom Implementation ===");
        Comparator<Person> anonymous = new Comparator<>() { public int compare(Person a, Person b){ return Integer.compare(a.getAge(), b.getAge()); } };
        Comparator<Person> lambda = (a,b) -> Integer.compare(a.getAge(), b.getAge());
        Comparator<Person> methodRef = Comparator.comparing(Person::getAge);
        List<Person> people = new ArrayList<>(List.of(Person.of("Charlie","Brown",35),Person.of("Alice","Smith",30),Person.of("Bob","Jones",25)));
        people.sort(methodRef);
        MappingAndSortingExamples.printNamesWithAges(people);
        people.sort(anonymous.reversed());
        MappingAndSortingExamples.printNamesWithAges(people);
    }
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
