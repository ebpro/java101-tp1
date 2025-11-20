package fr.univtln.bruno.samples.java101.tp3.list;

import fr.univtln.bruno.samples.java101.tp3.Person;
import fr.univtln.bruno.samples.java101.tp3.functional.MappingAndSortingExamples;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * Examples demonstrating common List implementations and idioms.
 *
 * <p>Each static method illustrates characteristics, complexity or pitfalls of a
 * specific List usage (ArrayList, LinkedList, subList, immutability...).</p>
 */
@Slf4j
public class ListExamples {
  /**
   * Private constructor to prevent instantiation.
   */
  private ListExamples() {
  }

  /**
   * ArrayList: access O(1) index, append amortized O(1), remove middle O(n).
   */
  public static void arrayListExample() {
    log.info("=== ArrayList Example ===");
    List<Person> people = new ArrayList<>();
    people.add(Person.of("Alice", "Smith", 30));
    people.add(Person.of("Bob", "Jones", 25));
    people.add(Person.of("Charlie", "Brown", 35));
    log.debug("Initial people list: {}", people);
    log.info("Size: {}", people.size());
    // safe access because we just added elements above
    log.info("First: {}", people.get(0).getFullName());
    log.info("Contains Alice? {}", people.contains(Person.of("Alice", "Smith", 30)));
    people.remove(1); // remove Bob
    log.debug("After remove index=1 list: {}", people);
    log.info("After remove index=1 size: {}", people.size());
    people.remove(Person.of("Alice", "Smith", 30));
    log.debug("After remove Alice list: {}", people);
    log.info("After remove Alice size: {}", people.size());
  }

  /**
   * LinkedList: insert/remove head/tail O(1), index access O(n).
   */
  public static void linkedListExample() {
    log.info("=== LinkedList Example ===");
    LinkedList<Person> people = new LinkedList<>();
    people.addLast(Person.of("Alice", "Smith", 30));
    people.addLast(Person.of("Bob", "Jones", 25));
    people.addFirst(Person.of("Charlie", "Brown", 35));
    log.info("First: {} Last: {} Size: {}", people.getFirst().getFullName(), people.getLast().getFullName(), people.size());
    people.offer(Person.of("David", "Wilson", 40));
    Person polled = people.poll();
    if (polled != null) log.info("Poll (FIFO): {}", polled.getFullName());
    people.push(Person.of("Eve", "Davis", 28));
    Person popped = people.pop();
    if (popped != null) log.info("Pop (LIFO): {}", popped.getFullName());
  }

  /**
   * Immutability vs unmodifiable view.
   */
  public static void immutableListExample() {
    log.info("=== Immutable Lists Example ===");
    List<String> immutable = List.of("A", "B", "C");
    log.info("Immutable: {}", immutable);
    // don't attempt to modify List.of() result; demonstrate safe usage instead
    List<String> backing = new ArrayList<>(List.of("X", "Y", "Z"));
    List<String> unmodifiable = Collections.unmodifiableList(backing);
    backing.add("W");
    log.info("Backing modified reflected in view: {}", unmodifiable);
  }

  /**
   * SubList: a view backed by the original list.
   */
  public static void subListExample() {
    log.info("=== SubList Example ===");
    List<Integer> numbers = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    List<Integer> slice = numbers.subList(3, 7);
    log.info("Slice [3,7): {}", slice);
    slice.set(0, 99);
    log.info("After set in slice original: {}", numbers);
    slice.clear();
    log.info("After clear slice original: {}", numbers);

    // Student note: subList is a view. If you need a detached copy, do:
    // List<Integer> detached = new ArrayList<>(numbers.subList(3,7));
  }

  /**
   * Sorting with Comparable then Comparator.
   */
  public static void sortingExample() {
    log.info("=== Sorting Example ===");
    List<Person> people = new ArrayList<>(List.of(
      Person.of("Charlie", "Brown", 35),
      Person.of("Alice", "Smith", 30),
      Person.of("Bob", "Jones", 25)
    ));
    log.debug("Before sort: {}", people);
    MappingAndSortingExamples.printFullNames(people);
    Collections.sort(people);
    log.debug("After natural sort: {}", people);
    MappingAndSortingExamples.printFullNames(people);
    people.sort(Comparator.comparing(Person::age).reversed());
    log.debug("After age-desc sort: {}", people);
    MappingAndSortingExamples.printNamesWithAges(people);
  }

  /**
   * Iteration: foreach, removeIf, ListIterator (bidirectional).
   */
  public static void iterationExample() {
    log.info("=== Iteration Example ===");
    List<String> items = new ArrayList<>(List.of("A", "B", "C", "D"));
    for (String i : items) {
      log.info("Foreach: {}", i);
    }
    // remove 'B' using removeIf instead of iterator.remove()
    // Uses a lambda expression (functional style)
    items.removeIf(s -> "B".equals(s));
    log.info("After removeIf remove B: {}", items);
    ListIterator<String> listIt = items.listIterator(items.size());
    while (listIt.hasPrevious()) {
      log.info("Backward: {}", listIt.previous());
    }

    // Student note: if you need to remove while iterating, don't use for-each; use Iterator explicitly:
    // Iterator<String> it = items.iterator();
    // while (it.hasNext()) {
    //   if (shouldRemove(it.next())) it.remove();
    // }
  }

  public static void main(String[] args) {
    arrayListExample();
    linkedListExample();
    immutableListExample();
    subListExample();
    sortingExample();
    iterationExample();
  }
}
