package fr.univtln.bruno.samples.java101.tp3.functional;

import fr.univtln.bruno.samples.java101.tp3.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Basic Stream API examples focused on collections: creation, intermediate operations and common idioms.
 */
@Slf4j
public class StreamBasicsExamples {
    public static void streamCreationExample() {
        log.info("=== Stream Creation ===");
        log.info("From list: {}", List.of("A","B","C").stream().toList());
        log.info("From array: {}", Arrays.stream(new String[]{"X","Y"}).toList());
        log.info("From values: {}", Stream.of("1","2","3").toList());
        log.info("Empty count: {}", Stream.empty().count());
        log.info("Iterate limit: {}", Stream.iterate(0,n->n+1).limit(5).toList());
        log.info("Generate random: {}", Stream.generate(Math::random).limit(3).toList());
        log.info("Range: {}", IntStream.range(1,5).boxed().toList());
        log.info("RangeClosed: {}", IntStream.rangeClosed(1,5).boxed().toList());
    }

    public static void filterExample() {
        log.info("=== Filter Example ===");
        List<Person> people = List.of(
                Person.of("Alice","Smith",30),
                Person.of("Bob","Jones",25),
                Person.of("Charlie","Brown",35),
                Person.of("David","Wilson",28)
        );
        log.info("Age >=30: {}", people.stream().filter(p->p.getAge()>=30).map(Person::getFullName).toList());
        log.info("Age>=25 & long lastName: {}", people.stream().filter(p->p.getAge()>=25).filter(p->p.getLastName().length()>5).map(Person::getFullName).toList());
    }

    public static void mapExample() {
        log.info("=== Map Example ===");
        List<Person> people = List.of(Person.of("Alice","Smith",30),Person.of("Bob","Jones",25));
        log.info("Full names: {}", people.stream().map(Person::getFullName).toList());
        log.info("Ages: {}", people.stream().map(Person::getAge).toList());
        log.info("Upper: {}", people.stream().map(Person::getFullName).map(String::toUpperCase).toList());
    }

    public static void flatMapExample() {
        log.info("=== FlatMap Example ===");
        List<List<String>> nested = List.of(List.of("A","B"), List.of("C"));
        log.info("Flatten: {}", nested.stream().flatMap(Collection::stream).toList());
        List<String> sentences = List.of("Hello World","Java Streams");
        log.info("Words: {}", sentences.stream().flatMap(s->Arrays.stream(s.split(" "))).toList());
    }

    public static void distinctSortedLimitExample() {
        log.info("=== Distinct/Sorted/Limit ===");
        List<Integer> nums = List.of(5,2,8,2,9,1,5);
        log.info("Distinct: {}", nums.stream().distinct().toList());
        log.info("Sorted: {}", nums.stream().sorted().toList());
        log.info("Top3 desc distinct: {}", nums.stream().distinct().sorted(Comparator.reverseOrder()).limit(3).toList());
        log.info("Skip first 2 sorted: {}", nums.stream().sorted().skip(2).toList());
    }

    public static void peekExample() {
        log.info("=== Peek Example ===");
        List<Integer> nums = List.of(1,2,3,4,5);
        List<Integer> result = nums.stream()
                .peek(n->log.info("Original {}", n))
                .filter(n-> n%2==0)
                .peek(n->log.info("Filtered {}", n))
                .map(n-> n*2)
                .peek(n->log.info("Mapped {}", n))
                .toList();
        log.info("Result {}", result);
    }

    public static void sortingExample() {
        log.info("=== Sorting Example ===");
        List<Person> people = List.of(Person.of("Charlie","Brown",35),Person.of("Alice","Smith",30),Person.of("Bob","Jones",25));
        log.info("Natural: {}", people.stream().sorted().map(Person::getFullName).toList());
        log.info("By age: {}", people.stream().sorted(Comparator.comparing(Person::getAge)).map(p->p.getFullName()+"("+p.getAge()+")").toList());
    }

    public static void takeWhileDropWhileExample() {
        log.info("=== takeWhile/dropWhile ===");
        List<Integer> numbers = List.of(1,2,3,4,5,6,7);
        log.info("takeWhile <4: {}", numbers.stream().takeWhile(n->n<4).toList());
        log.info("dropWhile <4: {}", numbers.stream().dropWhile(n->n<4).toList());
    }

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
