package fr.univtln.bruno.samples.java101.tp3.list;

import fr.univtln.bruno.samples.java101.tp3.Person;
import fr.univtln.bruno.samples.java101.tp3.functionnal.MappingAndSortingExamples;
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
    /** ArrayList: accès index O(1), ajout fin amorti O(1), suppression milieu O(n). */
    public static void arrayListExample() {
        log.info("=== ArrayList Example ===");
        List<Person> people = new ArrayList<>();
        people.add(Person.of("Alice", "Smith", 30));
        people.add(Person.of("Bob", "Jones", 25));
        people.add(Person.of("Charlie", "Brown", 35));
        log.info("Size: {}", people.size());
        log.info("First: {}", people.get(0).getFullName());
        log.info("Contains Alice? {}", people.contains(Person.of("Alice", "Smith", 30)));
        people.remove(1); // remove Bob
        log.info("After remove index=1 size: {}", people.size());
        people.remove(Person.of("Alice", "Smith", 30));
        log.info("After remove Alice size: {}", people.size());
    }

    /** LinkedList: insertion/suppression début/fin O(1), accès index O(n). */
    public static void linkedListExample() {
        log.info("=== LinkedList Example ===");
        LinkedList<Person> people = new LinkedList<>();
        people.addLast(Person.of("Alice", "Smith", 30));
        people.addLast(Person.of("Bob", "Jones", 25));
        people.addFirst(Person.of("Charlie", "Brown", 35));
        log.info("First: {} Last: {} Size: {}", people.getFirst().getFullName(), people.getLast().getFullName(), people.size());
        people.offer(Person.of("David", "Wilson", 40));
        log.info("Poll (FIFO): {}", people.poll().getFullName());
        people.push(Person.of("Eve", "Davis", 28));
        log.info("Pop (LIFO): {}", people.pop().getFullName());
    }

    /** Immutabilité vs vue non modifiable. */
    public static void immutableListExample() {
        log.info("=== Immutable Lists Example ===");
        List<String> immutable = List.of("A", "B", "C");
        log.info("Immutable: {}", immutable);
        try { immutable.add("D"); } catch (UnsupportedOperationException e) { log.info("Cannot modify immutable list"); }
        List<String> backing = new ArrayList<>(List.of("X","Y","Z"));
        List<String> unmodifiable = Collections.unmodifiableList(backing);
        backing.add("W");
        log.info("Backing modified reflected in view: {}", unmodifiable);
    }

    /** Sous-liste: vue liée à la liste d'origine. */
    public static void subListExample() {
        log.info("=== SubList Example ===");
        List<Integer> numbers = new ArrayList<>(List.of(0,1,2,3,4,5,6,7,8,9));
        List<Integer> slice = numbers.subList(3,7);
        log.info("Slice [3,7): {}", slice);
        slice.set(0, 99);
        log.info("After set in slice original: {}", numbers);
        slice.clear();
        log.info("After clear slice original: {}", numbers);
    }

    /** Tri avec Comparable puis Comparator. */
    public static void sortingExample() {
        log.info("=== Sorting Example ===");
        List<Person> people = new ArrayList<>(List.of(
                Person.of("Charlie", "Brown", 35),
                Person.of("Alice", "Smith", 30),
                Person.of("Bob", "Jones", 25)
        ));
        MappingAndSortingExamples.printFullNames(people);
        Collections.sort(people);
        MappingAndSortingExamples.printFullNames(people);
        people.sort(Comparator.comparing(Person::getAge).reversed());
        MappingAndSortingExamples.printNamesWithAges(people);
    }

    /** Itération: foreach, Iterator (suppression), ListIterator (bidirectionnel). */
    public static void iterationExample() {
        log.info("=== Iteration Example ===");
        List<String> items = new ArrayList<>(List.of("A","B","C","D"));
        for (String i: items) { log.info("Foreach: {}", i); }
        Iterator<String> it = items.iterator();
        while (it.hasNext()) { if ("B".equals(it.next())) it.remove(); }
        log.info("After iterator remove B: {}", items);
        ListIterator<String> listIt = items.listIterator(items.size());
        while (listIt.hasPrevious()) { log.info("Backward: {}", listIt.previous()); }
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
