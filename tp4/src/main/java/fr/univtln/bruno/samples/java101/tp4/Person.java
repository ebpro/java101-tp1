package fr.univtln.bruno.samples.java101.tp4;

/**
 * Immutable Person record used across TP4 examples.
 *
 * <p>Records (Java 14+) provide a concise way to declare immutable data carriers.
 * The compiler automatically generates:
 * - private final fields
 * - public accessor methods (name(), age(), email())
 * - equals(), hashCode(), toString()
 * - canonical constructor
 * </p>
 *
 * @param name  the person's name (must not be null or blank)
 * @param age   the person's age (must be >= 0)
 * @param email the person's email (optional, can be null)
 */
public record Person(String name, int age, String email) {

  /**
   * Compact constructor with validation.
   * Validates invariants before field initialization.
   *
   * @throws IllegalArgumentException if name is null/blank or age is negative
   */
  public Person {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Name must not be null or blank");
    }
    if (age < 0) {
      throw new IllegalArgumentException("Age must not be negative");
    }
  }

  /**
   * Convenience constructor without email.
   *
   * @param name the person's name
   * @param age  the person's age
   */
  public Person(String name, int age) {
    this(name, age, null);
  }

  /**
   * No-arg constructor for frameworks and method references (Supplier<Person> / Person::new).
   * Delegates to canonical constructor with safe default values.
   */
  public Person() {
    this("Unknown", 0, null);
  }

  /**
   * Checks if the person is an adult (age >= 18).
   *
   * @return true if adult, false otherwise
   */
  public boolean isAdult() {
    return age >= 18;
  }

  /**
   * Returns a new Person with updated age.
   * Records are immutable, so we create a new instance.
   *
   * @param newAge the new age
   * @return a new Person with the updated age
   */
  public Person withAge(int newAge) {
    return new Person(name, newAge, email);
  }

  /**
   * Returns a new Person with updated email.
   *
   * @param newEmail the new email
   * @return a new Person with the updated email
   */
  public Person withEmail(String newEmail) {
    return new Person(name, age, newEmail);
  }
}
