package fr.univtln.bruno.samples.java101.tp1.factory;

import fr.univtln.bruno.samples.java101.tp1.Person;
import java.util.Objects;

/**
 * Small pedagogical factory to create {@link Person} instances.
 *
 * <p>This class groups named factory methods that make intent explicit and
 * avoid the telescoping-constructor problem when many variants or default
 * values are required. The factory delegates to the {@code Person} constructor
 * in this sample, but in production code it could encapsulate more complex
 * creation logic (validation, configuration, caching, or dependency wiring).</p>
 *
 * <p>Factory methods are static methods that return an instance of a class.
 * They are an alternative to constructors and can have descriptive names
 * that clarify the purpose of the created instance.</p>
 *
 * <p>Common factory method names (see Effective Java, item about static factories):
 * <ul>
 *   <li><b>valueOf</b> — convert parameters into an instance that represents the same value.</li>
 *   <li><b>of</b> — a concise alternative to <em>valueOf</em> (used in several JDK APIs).</li>
 *   <li><b>getInstance</b> — return an instance described by the parameters (often used for singletons or cached instances).</li>
 *   <li><b>newInstance</b> — guarantee a new distinct instance on each call.</li>
 *   <li><b>getType / newType</b> — variants used when the factory is declared in a different class and returns a particular type.</li>
 * </ul>
 *
 */
public final class PersonFactory {

  /**
   * Private constructor to prevent instantiation of this static factory class.
   */
  private PersonFactory() { }

  /**
   * Create a {@link Person} with the given names and age.
   *
   * <p>This method validates its inputs and normalizes string values (calls
   * {@code String.strip()}). It throws a NullPointerException when a name is
   * {@code null} and IllegalArgumentException when the age is negative.</p>
   *
   * @param firstName first name (must not be null)
   * @param lastName last name (must not be null)
   * @param age age, must be >= 0
   * @return a new Person instance
   * @throws NullPointerException if firstName or lastName is null
   * @throws IllegalArgumentException if age is negative
   */
  public static Person of(String firstName, String lastName, int age) {
      Objects.requireNonNull(firstName, "firstName must not be null");
      Objects.requireNonNull(lastName, "lastName must not be null");
      if (age < 0) throw new IllegalArgumentException("age must be >= 0");
      // normalize whitespace at factory boundary
      return new Person(firstName.strip(), lastName.strip(), age);
  }

    /**
     * Create a Person with course default values (useful for demos and tests).
     * @return a Person pre-populated with default values
     */
    public static Person defaultPerson() {
        return of("John", "Doe", 30);
    }

    /**
     * Create a Person representing a teenager.
     * @param firstName first name
     * @param lastName last name
     * @return a Person with a teenager age
     */
    public static Person teenager(String firstName, String lastName) {
        return of(firstName, lastName, 15);
    }

 }
