package fr.univtln.bruno.samples.java101.tp3.set;

import fr.univtln.bruno.samples.java101.tp3.Person;
import fr.univtln.bruno.samples.java101.tp3.functionnal.DedupExamples;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * Examples demonstrating Set implementations and common set operations.
 *
 * <p>Illustrates HashSet/LinkedHashSet/TreeSet characteristics, set algebra (union/intersection/difference),
 * and immutability patterns.</p>
 */
@Slf4j
public class SetExamples {
    /**
     * Demonstrates HashSet usage, including duplicate handling and iteration.
     */
    public static void hashSetExample() {
        log.info("=== HashSet Example ===");
        Set<Person> people = new HashSet<>();
        people.add(Person.of("Alice","Smith",30));
        people.add(Person.of("Bob","Jones",25));
        people.add(Person.of("Charlie","Brown",35));
        boolean addedDuplicate = people.add(Person.of("Alice","Smith",30));
        log.info("Duplicate added? {}", addedDuplicate);
        log.info("Size: {}", people.size());
        people.forEach(p -> log.info("Person: {}", p.getFullName()));
    }
    /**
     * Demonstrates LinkedHashSet usage to maintain insertion order.
     */
    public static void linkedHashSetExample() {
        log.info("=== LinkedHashSet Example ===");
        Set<String> ordered = new LinkedHashSet<>(List.of("C","A","B"));
        ordered.forEach(s -> log.info("Order: {}", s));
    }
    /**
     * Demonstrates TreeSet usage for sorting elements, both natural and by custom comparator.
     */
    public static void treeSetExample() {
        log.info("=== TreeSet Example ===");
        Set<Person> sorted = new TreeSet<>();
        sorted.add(Person.of("Charlie","Brown",35));
        sorted.add(Person.of("Alice","Smith",30));
        sorted.add(Person.of("Bob","Jones",25));
        sorted.forEach(p -> log.info("Sorted: {}", p.getFullName()));
        Set<Person> byAge = new TreeSet<>(Comparator.comparing(Person::getAge));
        byAge.addAll(sorted);
        byAge.forEach(p -> log.info("AgeSorted: {} ({})", p.getFullName(), p.getAge()));
    }
    /**
     * Demonstrates common set operations: union, intersection, difference, and symmetric difference.
     */
    public static void setOperationsExample() {
        log.info("=== Set Operations Example ===");
        Set<String> s1 = new HashSet<>(Set.of("A","B","C","D"));
        Set<String> s2 = new HashSet<>(Set.of("C","D","E","F"));
        Set<String> union = new HashSet<>(s1); union.addAll(s2);
        Set<String> inter = new HashSet<>(s1); inter.retainAll(s2);
        Set<String> diff = new HashSet<>(s1); diff.removeAll(s2);
        Set<String> sym = new HashSet<>(union); sym.removeAll(inter);
        log.info("Union: {}", union);
        log.info("Inter: {}", inter);
        log.info("Diff: {}", diff);
        log.info("SymDiff: {}", sym);
    }
    /**
     * Demonstrates removal of duplicates from a collection using Set implementations.
     */
    public static void removeDuplicatesExample() {
        log.info("=== Remove Duplicates ===");
        List<String> withDup = List.of("A","B","A","C","B","D");
        DedupExamples.removeDuplicatesPreserveOrder(withDup);
    }
    /**
     * Demonstrates NavigableSet features like navigation methods and descending view.
     */
    public static void navigableSetExample() {
        log.info("=== NavigableSet Example ===");
        NavigableSet<Integer> nav = new TreeSet<>(Set.of(1,3,5,7,9));
        log.info("Lower(7): {} Floor(7): {} Ceiling(7): {} Higher(7): {}", nav.lower(7), nav.floor(7), nav.ceiling(7), nav.higher(7));
        log.info("Desc: {}", nav.descendingSet());
    }
    /**
     * Demonstrates immutable Set creation and attempts to modify it.
     */
    public static void immutableSetExample() {
        log.info("=== ImmutableSet Example ===");
        Set<String> imm = Set.of("A","B","C");
        try { imm.add("D"); } catch (UnsupportedOperationException e) { log.info("Immutable cannot add"); }
        Set<String> mutable = new HashSet<>(Set.of("X","Y"));
        Set<String> view = Collections.unmodifiableSet(mutable);
        mutable.add("Z");
        log.info("View reflects mutable: {}", view);
    }
    /**
     * Main method to run all examples.
     */
    public static void main(String[] args) {
        hashSetExample();
        linkedHashSetExample();
        treeSetExample();
        setOperationsExample();
        removeDuplicatesExample();
        immutableSetExample();
        navigableSetExample();
    }
}
