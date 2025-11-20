package fr.univtln.bruno.samples.java101.tp3.set;

import fr.univtln.bruno.samples.java101.tp3.Person;
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
   * Private constructor to prevent instantiation.
   */
  private SetExamples() {
  }

  /**
   * Demonstrates HashSet usage, including duplicate handling and iteration.
   * The Person class must correctly implement equals() and hashCode() for this to work as expected.
   * In case of a TreeSet instead of HashSet, Person must implement Comparable<Person>.
   */
  public static void hashSetExample() {
    log.info("=== HashSet Example ===");
    Set<Person> people = new HashSet<>();
    people.add(Person.of("Alice", "Smith", 30));
    people.add(Person.of("Bob", "Jones", 25));
    people.add(Person.of("Charlie", "Brown", 35));
    boolean addedDuplicate = people.add(Person.of("Alice", "Smith", 30));
    log.debug("HashSet internal state after adds: {}", people);
    log.info("Duplicate added? {}", addedDuplicate);
    log.info("Size: {}", people.size());
    people.forEach(p -> log.info("Person: {}", p.getFullName()));
  }

  /**
   * Demonstrates LinkedHashSet usage to maintain insertion order.
   * Person class must implement equals() and hashCode() correctly.
   * LinkedHashSet preserves the order in which elements were added.
   */
  public static void linkedHashSetExample() {
    log.info("=== LinkedHashSet Example ===");
    Set<String> ordered = new LinkedHashSet<>(List.of("C", "A", "B"));
    ordered.forEach(s -> log.info("Order: {}", s));
  }

  /**
   * Demonstrates TreeSet usage for sorting elements, both natural and by custom comparator.
   * Person class must implement Comparable<Person> for natural ordering.
   */
  public static void treeSetExample() {
    log.info("=== TreeSet Example ===");
    Set<Person> sorted = new TreeSet<>();
    sorted.add(Person.of("Charlie", "Brown", 35));
    sorted.add(Person.of("Alice", "Smith", 30));
    sorted.add(Person.of("Bob", "Jones", 25));
    log.debug("TreeSet elements (iteration order): {}", sorted);
    sorted.forEach(p -> log.info("Sorted: {}", p.getFullName()));
    Set<Person> byAge = new TreeSet<>(Comparator.comparing(Person::age));
    byAge.addAll(sorted);
    log.debug("Age-sorted set: {}", byAge);
    byAge.forEach(p -> log.info("AgeSorted: {} ({})", p.getFullName(), p.age()));
  }

  /**
   * Demonstrates common set operations: union, intersection, difference, and symmetric difference.
   */
  public static void setOperationsExample() {
    log.info("=== Set Operations Example ===");
    Set<String> s1 = new HashSet<>(Set.of("A", "B", "C", "D"));
    Set<String> s2 = new HashSet<>(Set.of("C", "D", "E", "F"));
    Set<String> union = new HashSet<>(s1);
    union.addAll(s2);
    Set<String> inter = new HashSet<>(s1);
    inter.retainAll(s2);
    Set<String> diff = new HashSet<>(s1);
    diff.removeAll(s2);
    Set<String> sym = new HashSet<>(union);
    sym.removeAll(inter);
    log.debug("s1={}, s2={}", s1, s2);
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
    List<String> withDup = List.of("A", "B", "A", "C", "B", "D");
    fr.univtln.bruno.samples.java101.tp3.set.DedupCollections.removeDuplicatesPreserveOrder(withDup);
  }

  /**
   * Demonstrates NavigableSet features like navigation methods and descending view.
   * A NavigableSet is a SortedSet with additional navigation methods : lower, floor, ceiling, higher, and descendingSet.
   */
  public static void navigableSetExample() {
    log.info("=== NavigableSet Example ===");
    NavigableSet<Integer> nav = new TreeSet<>(Set.of(1, 3, 5, 7, 9));
    log.info("Lower(7): {} Floor(7): {} Ceiling(7): {} Higher(7): {}", nav.lower(7), nav.floor(7), nav.ceiling(7), nav.higher(7));
    log.info("Desc: {}", nav.descendingSet());
  }
  /**
   * Main method to run all examples.
   */

  /**
   * Demonstrates immutable Set creation and attempts to modify it.
   */
  public static void immutableSetExample() {
    log.info("=== ImmutableSet Example ===");
    Set<String> imm = Set.of("A", "B", "C");
    try {
      imm.add("D");
    } catch (UnsupportedOperationException e) {
      log.info("Immutable cannot add");
    }
    Set<String> mutable = new HashSet<>(Set.of("X", "Y"));
    Set<String> view = Collections.unmodifiableSet(mutable);
    mutable.add("Z");
    log.info("View reflects mutable: {}", view);
  }

  /**
   * Runner for set examples.
   *
   * @param args ignored
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
