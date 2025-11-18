package fr.univtln.bruno.samples.java101.tp1.immutable;

import java.util.Objects;

/**
 * Immutable representation of a person.
 *
 * <p>This class is a small, thread-safe value object: all fields are final and
 * set at construction time. Construction is intentionally delegated to the
 * nested {@link Builder}: the Builder performs validation and normalization
 * (for example, trimming/stripping input) when {@link Builder#build()} is
 * invoked and will raise appropriate exceptions for invalid input. The class
 * constructor is private to enforce use of the Builder for public construction.
 *</p>
 *
 * <p>Usage notes:
 * <ul>
 *   <li>Instances are immutable and safe to share between threads.</li>
 *   <li>Use the {@link Builder} to create validated instances; intermediate
 *       builder states may contain {@code null} or invalid values until
 *       {@link Builder#build()} is called.</li>
 *   <li>Validation and normalization are performed by the nested {@link Builder}
 *       when {@link Builder#build()} is invoked; the constructor is private and
 *       expects already-validated values.</li>
 * </ul>
 */
public final class PersonImmutable {
    private final String firstName;
    private final String lastName;
    private final int age;

    /**
     * Private constructor. Instances should be created via {@link Builder}.
     * The Builder is responsible for validating and normalizing inputs before
     * calling this constructor.
     *
     * @param firstName non-null, already-normalized first name
     * @param lastName non-null, already-normalized last name
     * @param age validated age (>= 0)
     */
    private PersonImmutable(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    /**
     * Return the first name.
     *
     * @return the first name (never {@code null})
     */
    public String getFirstName() { return firstName; }

    /**
     * Return the last name.
     *
     * @return the last name (never {@code null})
     */
    public String getLastName() { return lastName; }

    /**
     * Return the age.
     *
     * @return the age (>= 0)
     */
    public int getAge() { return age; }

    @Override
    public String toString() { return firstName + " " + lastName + "(" + age + ")"; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonImmutable)) return false;
        PersonImmutable that = (PersonImmutable) o;
        return age == that.age && firstName.equals(that.firstName) && lastName.equals(that.lastName);
    }

    @Override
    public int hashCode() { return Objects.hash(firstName, lastName, age); }

    /**
     * Create a new {@link Builder} to construct immutable instances.
     *
     * @return a fresh Builder
     */
    public static Builder builder() { return new Builder(); }

    /**
     * Builder for {@link PersonImmutable}.
     *
     * <p>The builder allows setting properties in any order. Validation is
     * performed when {@link #build()} is invoked; thus intermediate builder
     * states may contain {@code null} or invalid values.</p>
     */
    public static final class Builder {
        private String firstName;
        private String lastName;
        private int age;

        /**
         * Public no-arg constructor for Builder (documented to satisfy Javadoc checks).
         */
        public Builder() {
            // intentionally empty; builder fields are initialized to defaults
        }

        /**
         * Set the first name to use when building the instance.
         *
         * @param f the first name (may be {@code null} until {@link #build()} is called)
         * @return this builder
         */
        public Builder firstName(String f) { this.firstName = f; return this; }

        /**
         * Set the last name to use when building the instance.
         *
         * @param l the last name (may be {@code null} until {@link #build()} is called)
         * @return this builder
         */
        public Builder lastName(String l) { this.lastName = l; return this; }

        /**
         * Set the age to use when building the instance.
         *
         * @param a the age (may be negative until {@link #build()} is called)
         * @return this builder
         */
        public Builder age(int a) { this.age = a; return this; }

        /**
         * Build a validated {@link PersonImmutable} instance.
         *
         * @return a new PersonImmutable
         * @throws NullPointerException if a required name is {@code null}
         * @throws IllegalArgumentException if age is negative
         */
        public PersonImmutable build() {
            // validate required fields and normalize strings at the builder boundary
            Objects.requireNonNull(firstName, "firstName must not be null");
            Objects.requireNonNull(lastName, "lastName must not be null");
            if (age < 0) throw new IllegalArgumentException("age must be >= 0");
            String fn = firstName.strip();
            String ln = lastName.strip();
            return new PersonImmutable(fn, ln, age);
        }
    }
}
