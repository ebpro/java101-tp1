package fr.univtln.bruno.samples.java101.tp1.factory;

import java.util.Locale;
import java.util.Objects;

/**
 * Simple Person class that demonstrates the private all-arguments constructor
 * pattern with named factory methods. Instances are created only through the
 * static factory methods {@link #of(String,String,int)}, {@link #defaultPerson()} and
 * {@link #teenager(String,String)}.
 */
public final class PersonWithFactory {

    private final String firstName;
    private final String lastName;
    private final int age;

    /**
     * Private all-args constructor. Instances must be created via factory methods.
     */
    private PersonWithFactory(String firstName, String lastName, int age) {
        this.firstName = Objects.requireNonNull(firstName, "firstName must not be null");
        this.lastName = Objects.requireNonNull(lastName, "lastName must not be null");
        if (age < 0) throw new IllegalArgumentException("age must be >= 0");
        this.age = age;
    }

    /**
     * Create a new validated person instance.
     *
     * @param firstName first name (not null)
     * @param lastName last name (not null)
     * @param age age, must be >= 0
     * @return a validated, immutable {@link PersonWithFactory}
     */
    public static PersonWithFactory of(String firstName, String lastName, int age) {
        String fn = Objects.requireNonNull(firstName, "firstName must not be null").strip();
        String ln = Objects.requireNonNull(lastName, "lastName must not be null").strip();
        if (age < 0) throw new IllegalArgumentException("age must be >= 0");
        return new PersonWithFactory(fn, ln, age);
    }

    /**
     * Default example person useful for demos and tests.
     *
     * @return an example {@link PersonWithFactory} with default values
     */
    public static PersonWithFactory defaultPerson() {
        return of("John", "Doe", 30);
    }

    /**
     * Convenience factory for a teenager example.
     *
     * @param firstName first name (not null)
     * @param lastName last name (not null)
     * @return an example teen {@link PersonWithFactory}
     */
    public static PersonWithFactory teenager(String firstName, String lastName) {
        return of(firstName, lastName, 15);
    }

    /**
     * Returns the person's first name.
     *
     * @return first name (never null)
     */
    public String getFirstName() { return firstName; }

    /**
     * Returns the person's last name.
     *
     * @return last name (never null)
     */
    public String getLastName() { return lastName; }

    /**
     * Returns the person's age.
     *
     * @return age in years
     */
    public int getAge() { return age; }

    @Override
    public String toString() {
        return String.format("%s, %s (%d)", lastName.toUpperCase(Locale.ROOT), firstName.toUpperCase(Locale.ROOT), age);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonWithFactory that = (PersonWithFactory) o;
        return age == that.age && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age);
    }
}
