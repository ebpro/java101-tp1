package fr.univtln.bruno.samples.java101.tp3;

import lombok.With;
import lombok.NonNull;

import java.util.Comparator;
import java.util.Objects;

/**
 * Immutable value object representing a person used in TP3 collection examples.
 *
 * <p>Instances are created via the static factory {@link #of(String, String, int)} which
 * performs basic validation. The class implements {@link Comparable} to provide a
 * natural ordering (lastName, firstName, age).</p>
 *
 * <p>Pedagogical note on <code>@With</code> and Java records:
 * - Lombok's <code>@With</code> is applied to record components to generate immutable
 *   "withX(...)" methods that are convenient in exercises where you want to
 *   create a slightly modified copy (for example <code>p.withAge(31)</code>).
 * - Java records already provide <code>equals</code>, <code>hashCode</code> and
 *   <code>toString</code>. Lombok is used only to generate helper methods such as
 *   the <code>with</code> methods. Ensure annotation processing is enabled in the IDE
 *   or build so generated methods are visible.
 *
 * Important edge cases to illustrate in class:
 * - Null handling: prefer factories (e.g. <code>of</code>) that validate parameters
 *   instead of silently accepting null fields.
 * - equals / compareTo consistency: ensure that <code>compareTo(a,b)==0</code>
 *   implies <code>a.equals(b)</code> to avoid surprising behavior in sorted
 *   collections (e.g. <code>TreeSet</code> / <code>TreeMap</code>).
 * - Concurrent modification: modifying a collection while iterating can throw
 *   <code>ConcurrentModificationException</code>. Demonstrate safe patterns such as
 *   using an explicit <code>Iterator</code> and <code>iterator.remove()</code>, or
 *   using concurrent collections like <code>CopyOnWriteArrayList</code> when appropriate.
 * </p>
 */
public record Person(@With String firstName, @With String lastName, @With int age) implements Comparable<Person> {
  /**
   * Create a new {@link Person} instance after validating arguments.
   *
   * @param firstName non-null, non-blank first name
   * @param lastName  non-null, non-blank last name
   * @param age       non-negative age
   * @return a new immutable Person
   * @throws IllegalArgumentException if any parameter is invalid
   */
  public static Person of(String firstName, String lastName, int age) {
    Objects.requireNonNull(firstName, "First name cannot be null");
    Objects.requireNonNull(lastName, "Last name cannot be null");
    String fn = firstName.trim();
    String ln = lastName.trim();
    if (fn.isBlank()) throw new IllegalArgumentException("First name cannot be blank");
    if (ln.isBlank()) throw new IllegalArgumentException("Last name cannot be blank");
    if (age < 0) throw new IllegalArgumentException("Age cannot be negative");
    return new Person(fn, ln, age);
  }

  /**
   * Natural ordering: by last name, then first name, then age.
   *
   * @param other the other person to compare with
   * @return negative/zero/positive as this is less/equal/greater than other
   */
  @Override
  public int compareTo(@NonNull Person other) {
    // Comparable contract expects non-null argument; Lombok @NonNull documents and enforces this
    return Comparator
      .comparing(Person::lastName)
      .thenComparing(Person::firstName)
      .thenComparingInt(Person::age)
      .compare(this, other);
  }

  /**
   * Return the human-friendly full name combining first and last name.
   *
   * @return full name ("First Last")
   */
  public String getFullName() {
    return firstName + " " + lastName;
  }

  /**
   * Small demo for students showing the Lombok-generated `with` methods on record components.
   * This main method is intentionally minimal: it creates a Person, uses the generated
   * withAge / withFirstName methods to create modified copies, and prints results so
   * students can see the immutability behavior.
   */
  public static void main(String[] args) {
    Person p = Person.of("Alice", "Smith", 30);
    System.out.println("Original: " + p);

    // Lombok generates withAge(int) and withFirstName(String) because of @With on components
    Person older = p.withAge(31);
    System.out.println("After withAge(31): " + older);

    Person renamed = p.withFirstName("Alicia");
    System.out.println("After withFirstName(\"Alicia\"): " + renamed);

    // original instance remains unchanged (immutability)
    System.out.println("Original still: " + p);
  }
}
