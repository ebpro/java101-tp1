package fr.univtln.bruno.samples.java101.tp1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.Objects;

/**
 * Represents a person with a first name, last name and age.
 *
 * <p>This class demonstrates instance state, classical getters/setters, a single canonical constructor,
 * and a class-level counter. </p>
 *
 * <p><strong>Construction note:</strong> this sample keeps a single canonical constructor
 * (firstName, lastName, age) on purpose to demonstrate the limitation of using multiple
 * overloaded constructors when optional parameters or many variants are required. The
 * constructor approach quickly becomes hard to read and maintain (the "telescoping"
 * or constructor-explosion problem). For flexible construction prefer the dedicated
 * {@code PersonWithFactory} or a builder: see {@link fr.univtln.bruno.samples.java101.tp1.factory.PersonWithFactory}.</p>
 *
 * <p>Design notes and guarantees:
 * <ul>
 *   <li>The constructor validates its arguments: {@code firstName} and {@code lastName}
 *       must not be {@code null} (an {@link NullPointerException} is thrown); {@code age}
 *       must be &gt;= 0 (an {@link IllegalArgumentException} is thrown).</li>
 *   <li>The class exposes conventional JavaBean-style getters/setters. For the immutable
 *       carrier-style API (compact accessors) a separate record-based example exists in
 *       the course material.</li>
 *   <li>The class is <em>not</em> thread-safe: concurrent mutation of instances or concurrent
 *       reads/writes of the {@code instanceCount} field are not synchronized. </li>
 *   <li>The {@code instanceCount} static field counts the number of constructed instances (the
 *       canonical constructor increments it). It is provided only for demonstration and testing
 *       and should not be used as a robust production metric.</li>
 * </ul>
 */
public class Person {

  private static final Logger logger = LoggerFactory.getLogger(Person.class);
  // class-level variable
  private static int instanceCount;

  // static initialization block
  static {
    // Warning: Thread safety not guaranteed here, we should use AtomicInteger or synchronized block for production code
    instanceCount = 0; // initialization
    // very small pedagogical hook: print instance count at JVM shutdown
    Runtime.getRuntime().addShutdownHook(new Thread(() ->
      logger.info("[Person] instances created: {}", instanceCount)
    ));
  }

  // Instance fields
  private String firstName;
  private String lastName;
  private int age;

  /**
   * Creates a new {@code Person} with the given values.
   *
   * <p>Validation performed:
   * <ul>
   *   <li>{@code firstName} and {@code lastName} must not be {@code null}.</li>
   *   <li>{@code age} must be greater than or equal to 0.</li>
   * </ul>
   *
   * @param firstName the first name (must not be {@code null})
   * @param lastName  the last name (must not be {@code null})
   * @param age       the person's age, must be >= 0
   * @throws NullPointerException     if {@code firstName} or {@code lastName} is {@code null}
   * @throws IllegalArgumentException if {@code age} is negative
   */
  public Person(String firstName, String lastName, int age) {
    this.firstName = normalizeName(firstName, "firstName");
    this.lastName = normalizeName(lastName, "lastName");
    if (age < 0) throw new IllegalArgumentException("age must be >= 0");
    this.age = age;
    instanceCount++;
  }

  // Helper to avoid duplication: validate and normalize name inputs
  private static String normalizeName(String value, String paramName) {
    String v = Objects.requireNonNull(value, paramName + " must not be null");
    return v.strip();
  }

  // Classic JavaBean-style accessors (preferred for this mutable example)

  /**
   * Returns the number of instances created via the canonical constructor.
   * Note: this counter is not synchronized and is meant for demonstration/testing only.
   * IT IS NOT A ROBUST PRODUCTION METRIC AND MAY NOT BE ACCURATE IN MULTI-THREADED CONTEXTS.
   *
   * @return number of created {@code Person} instances
   */
  public static int getInstanceCount() {
    return instanceCount;
  }

  /**
   * Returns the first name.
   *
   * @return current first name (may be empty string, never null when constructed)
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Set the first name. Normalizes and validates input.
   * @param firstName new first name (must not be null)
   */
  public void setFirstName(String firstName) {
    this.firstName = normalizeName(firstName, "firstName");
  }

  /**
   * Returns the last name.
   *
   * @return current last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Set the last name. Normalizes and validates input.
   * @param lastName new last name (must not be null)
   */
  public void setLastName(String lastName) {
    this.lastName = normalizeName(lastName, "lastName");
  }

  /**
   * Returns the age.
   *
   * @return current age
   */
  public int getAge() {
    return age;
  }

  /**
   * Set the age of the person.
   * @param age new age (must be >= 0)
   * @throws IllegalArgumentException if age is negative
   */
  public void setAge(int age) {
    if (age < 0) throw new IllegalArgumentException("age must be >= 0");
    this.age = age;
  }

  /**
   * Returns the full name composed of first name and last name separated by a single space.
   *
   * @return the full name (never {@code null})
   */
  public String fullName() {
    return firstName + " " + lastName;
  }

  /**
   * Returns a string representation of this person in the format:
   * "LASTNAME, FIRSTNAME (AGE)" with names in uppercase.
   *
   * @return string representation of this person
   */
  @Override
  public String toString() {
    return String.format("%s, %s (%d)", lastName.toUpperCase(Locale.ROOT), firstName.toUpperCase(Locale.ROOT), age);
  }

  /**
   * Compares this person to another object for equality.
   * Two persons are equal if they have the same first name, last name and age.
   *
   * @param o the object to compare with
   * @return {@code true} if the other object is a {@code Person} with the same state, {@code false} otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Person person = (Person) o;
    return age == person.age && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName);
  }

  /**
   * Returns the hash code for this person based on first name, last name and age.
   * Uses {@link Objects#hash(Object...)} for hash code computation to deal with nulls.
   *
   * @return hash code of this person
   */
  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, age);
  }

}
