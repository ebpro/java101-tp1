package fr.univtln.bruno.samples.java101.tp3;

import lombok.Value;
import lombok.With;

/**
 * Immutable value object representing a person used in TP3 collection examples.
 *
 * <p>Instances are created via the static factory {@link #of(String,String,int)} which
 * performs basic validation. The class implements {@link Comparable} to provide a
 * natural ordering (lastName, firstName, age).</p>
 */
@Value
public class Person implements Comparable<Person> {
    @With String firstName;
    @With String lastName;
    @With int age;

    /**
     * Create a new {@link Person} instance after validating arguments.
     *
     * @param firstName non-null, non-blank first name
     * @param lastName non-null, non-blank last name
     * @param age non-negative age
     * @return a new immutable Person
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public static Person of(String firstName, String lastName, int age) {
        if (firstName == null || firstName.isBlank()) throw new IllegalArgumentException("First name cannot be null or blank");
        if (lastName == null || lastName.isBlank()) throw new IllegalArgumentException("Last name cannot be null or blank");
        if (age < 0) throw new IllegalArgumentException("Age cannot be negative");
        return new Person(firstName, lastName, age);
    }

    /**
     * Natural ordering: by last name, then first name, then age.
     *
     * @param other the other person to compare with
     * @return negative/zero/positive as this is less/equal/greater than other
     */
    @Override
    public int compareTo(Person other) {
        int ln = lastName.compareTo(other.lastName);
        if (ln != 0) return ln;
        int fn = firstName.compareTo(other.firstName);
        if (fn != 0) return fn;
        return Integer.compare(age, other.age);
    }

    /**
     * Return a human-friendly full name combining first and last name.
     *
     * @return full name ("First Last")
     */
    public String getFullName() { return firstName + " " + lastName; }
}
