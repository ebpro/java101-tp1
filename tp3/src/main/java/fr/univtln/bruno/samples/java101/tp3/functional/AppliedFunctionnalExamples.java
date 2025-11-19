package fr.univtln.bruno.samples.java101.tp3.functional;

import fr.univtln.bruno.samples.java101.tp3.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Applied functional examples that operate on collections using the Stream API.
 *
 * <p>These examples were moved from other TP3 subpackages to centralize all
 * functional idioms in the `functional` package.</p>
 */
@Slf4j
public class AppliedFunctionnalExamples {

    /**
     * Demonstrate mapping and collecting on a list of Person instances.
     */
    public static void listStreamsExample() {
        log.info("=== List stream mapping example ===");
        List<Person> people = List.of(
                Person.of("Charlie","Brown",35),
                Person.of("Alice","Smith",30),
                Person.of("Bob","Jones",25)
        );
        log.info("Original names: {}", people.stream().map(Person::getFullName).toList());
        log.info("Names with ages: {}", people.stream().map(p -> p.getFullName() + "(" + p.getAge() + ")").toList());
    }

    /**
     * Demonstrate streaming over grouped values (e.g. after a grouping operation) to extract names.
     */
    public static void mapStreamGroupingExample() {
        log.info("=== Map grouping stream example ===");
        List<Person> people = List.of(
                Person.of("Alice","Smith",30),
                Person.of("Bob","Jones",25),
                Person.of("Charlie","Brown",35),
                Person.of("David","Smith",40)
        );
        Map<String, List<Person>> byLast = people.stream().collect(Collectors.groupingBy(Person::getLastName));
        byLast.forEach((ln, ps) -> log.info("{} -> {}", ln, ps.stream().map(Person::getFullName).toList()));
    }

    public static void main(String[] args) {
        listStreamsExample();
        mapStreamGroupingExample();
    }
}
