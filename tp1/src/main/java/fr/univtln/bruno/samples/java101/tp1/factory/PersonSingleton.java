package fr.univtln.bruno.samples.java101.tp1.factory;

/**
 * Simple educational singleton that holds person attributes directly.
 *
 * <p>This class demonstrates a configurable singleton pattern using the
 * initialization-on-demand idiom (double-checked locking variant here to
 * allow explicit parameterized initialization).</p>
 *
 * Usage examples:
 * <pre>
 *   // Option A: provide parameters explicitly once before first use
 *   PersonSingleton.initialize("Alice", "Smith", 28);
 *   PersonSingleton p = PersonSingleton.getInstance();
 *
 *   // Option B: use the default instance (created on first access)
 *   PersonSingleton p2 = PersonSingleton.getInstance();
 * </pre>
 *
 * Notes for students:
 * - Call {@link #initialize(String,String,int)} only once before first use if you
 *   want custom values; subsequent calls have no effect and return the existing
 *   instance.
 * - The implementation is thread-safe and lazy: the instance is created only when
 *   needed.
 */
public final class PersonSingleton {

    // Embedded person attributes (example shared resource)
    private final String firstName;
    private final String lastName;
    private final int age;

    // Volatile singleton instance for double-checked locking
    private static volatile PersonSingleton INSTANCE;

    // Private constructor prevents external instantiation
    private PersonSingleton(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    /**
     * Initialize the singleton with the provided parameters. If the singleton has
     * already been created, this call has no effect and the existing instance is
     * returned.
     *
     * @param firstName person's first name (non-null)
     * @param lastName person's last name (non-null)
     * @param age person's age (non-negative)
     * @return the singleton instance
     * @throws NullPointerException if firstName or lastName is null
     * @throws IllegalArgumentException if age is negative
     */
    public static PersonSingleton initialize(String firstName, String lastName, int age) {
        if (firstName == null) throw new NullPointerException("firstName must not be null");
        if (lastName == null) throw new NullPointerException("lastName must not be null");
        if (age < 0) throw new IllegalArgumentException("age must not be negative");

        if (INSTANCE == null) {
            synchronized (PersonSingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PersonSingleton(firstName, lastName, age);
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Return the singleton instance. If it wasn't initialized explicitly, a default
     * instance is created on first access (with conservative example values).
     *
     * @return the singleton instance
     */
    public static PersonSingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (PersonSingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PersonSingleton("John", "Doe", 30);
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Returns the person's first name.
     *
     * @return the person's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the person's last name.
     *
     * @return the person's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the person's age in years.
     *
     * @return the person's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the person's full name (first + space + last).
     *
     * @return the person's full name
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Convenience: whether the singleton has been initialized (explicitly or by
     * lazy default initialization).
     *
     * @return true if the singleton instance exists
     */
    public static boolean isInitialized() {
        return INSTANCE != null;
    }

}
