package fr.univtln.bruno.samples.java101.tp3.functional;

import fr.univtln.bruno.samples.java101.tp3.Book;
import fr.univtln.bruno.samples.java101.tp3.Person;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Collector-focused examples: grouping, partitioning, joining, summarizing and reduction.
 */
@Slf4j
public class CollectorsExamples {
    public static void terminalOperationsExample() {
        log.info("[functional] Terminal operations: count/any/all/none/findFirst");
        List<Integer> nums = List.of(1,2,3,4,5,6);
        long evenCount = nums.stream().filter(n -> n % 2 == 0).count();
        boolean anyGt4 = nums.stream().anyMatch(n -> n > 4);
        boolean allPositive = nums.stream().allMatch(n -> n > 0);
        boolean noneNegative = nums.stream().noneMatch(n -> n < 0);
        Optional<Integer> firstGt3 = nums.stream().filter(n -> n > 3).findFirst();
        log.info("evenCount={} anyGt4={} allPositive={} noneNegative={} firstGt3={}", evenCount, anyGt4, allPositive, noneNegative, firstGt3.orElse(-1));
    }

    public static void groupingAndCountingExample() {
        log.info("[functional] groupingBy and counting");
        List<Person> people = List.of(Person.of("Alice","Smith",30), Person.of("Bob","Jones",25), Person.of("Charlie","Brown",35), Person.of("David","Smith",28));
        Map<String, List<Person>> byLast = people.stream().collect(Collectors.groupingBy(Person::getLastName));
        byLast.forEach((ln, ps) -> log.info("{} -> {}", ln, ps.stream().map(Person::getFullName).toList()));
        Map<String, Long> counts = people.stream().collect(Collectors.groupingBy(Person::getLastName, Collectors.counting()));
        log.info("counts: {}", counts);
    }

    public static void complexCollectorsBooksExample() {
        log.info("[functional] complex collectors for Book examples");
        List<Book> books = List.of(
                Book.of("978-0134685991","Effective Java","Joshua Bloch", LocalDate.of(2018,1,6),45.99),
                Book.of("978-0596009205","Head First Java","Kathy Sierra", LocalDate.of(2005,2,9),39.99),
                Book.of("978-0134685992","Java Concurrency","Brian Goetz", LocalDate.of(2006,5,19),42.99)
        );
        Map<String, List<String>> titlesByAuthor = books.stream().collect(Collectors.groupingBy(Book::getAuthor, Collectors.mapping(Book::getTitle, Collectors.toList())));
        titlesByAuthor.forEach((a,t) -> log.info("{} -> {}", a, t));
    }
}
