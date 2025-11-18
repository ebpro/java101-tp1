package fr.univtln.bruno.samples.java101.tp1.immutable;

import java.util.Objects;

/**
 * Immutable value object representing a person.
 *
 * <p>Instances of this record are immutable. Modification methods (withX/increment/merge)
 * return new instances instead of mutating the current one.</p>
 *
 * <p>Components:
 * <ul>
 *   <li>{@code id} - unique identifier, must be non-null</li>
 *   <li>{@code name} - person name, must be non-null</li>
 *   <li>{@code email} - contact email, must be non-null</li>
 *   <li>{@code age} - non-negative integer representing age</li>
 * </ul>
 *
 * <p>Invariants are validated in the compact constructor.</p>
 *
 * @param id non-null unique identifier
 * @param name non-null person name
 * @param email non-null contact email
 * @param age non-negative integer representing age
 */
public record PersonValueObject(String id, String name, String email, int age) {
    /**
     * Compact canonical constructor performing basic validation.
     *
     * @param id non-null unique identifier
     * @param name non-null person name
     * @param email non-null contact email
     * @param age non-negative integer age
     * @throws NullPointerException if {@code id}, {@code name} or {@code email} is null
     * @throws IllegalArgumentException if {@code age} is negative
     */
    public PersonValueObject {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(email, "email must not be null");
        if (age < 0) throw new IllegalArgumentException("age must be >= 0");
    }

    /**
     * Factory method to create a new PersonRecord.
     *
     * @param id non-null unique identifier
     * @param name non-null person name
     * @param email non-null contact email
     * @param age non-negative age
     * @return a new {@code PersonRecord} instance
     * @throws NullPointerException if {@code id}, {@code name} or {@code email} is null
     * @throws IllegalArgumentException if {@code age} is negative
     */
    public static PersonValueObject of(String id, String name, String email, int age) {
        return new PersonValueObject(id, name, email, age);
    }

    /**
     * Return a new instance with the given name.
     *
     * @param newName non-null new name
     * @return a new {@code PersonRecord} with {@code name} set to {@code newName}
     * @throws NullPointerException if {@code newName} is null
     */
    public PersonValueObject withName(String newName) {
        return new PersonValueObject(this.id, Objects.requireNonNull(newName, "name must not be null"), this.email, this.age);
    }

    /**
     * Return a new instance with the given email.
     *
     * @param newEmail non-null new email
     * @return a new {@code PersonRecord} with {@code email} set to {@code newEmail}
     * @throws NullPointerException if {@code newEmail} is null
     */
    public PersonValueObject withEmail(String newEmail) {
        return new PersonValueObject(this.id, this.name, Objects.requireNonNull(newEmail, "email must not be null"), this.age);
    }

    /**
     * Return a new instance with the given age.
     *
     * @param newAge non-negative age
     * @return a new {@code PersonRecord} with {@code age} set to {@code newAge}
     * @throws IllegalArgumentException if {@code newAge} is negative
     */
    public PersonValueObject withAge(int newAge) {
        if (newAge < 0) throw new IllegalArgumentException("age must be >= 0");
        return new PersonValueObject(this.id, this.name, this.email, newAge);
    }

    /**
     * Return a new instance with the age incremented by one.
     *
     * @return a new {@code PersonRecord} with {@code age} = current age + 1
     */
    public PersonValueObject incrementAge() {
        return new PersonValueObject(this.id, this.name, this.email, this.age + 1);
    }

    /**
     * Merge non-null fields from another record into this one and return the result.
     *
     * <p>Behavior:
     * <ul>
     *   <li>If {@code other} is {@code null}, returns {@code this}.</li>
     *   <li>For {@code name} and {@code email}, non-null values from {@code other} replace current values.</li>
     *   <li>For {@code age}, {@code other.age} is used if it is >= 0; otherwise current age is retained.</li>
     * </ul>
     *
     * @param other another {@code PersonRecord} to merge from, may be null
     * @return a new {@code PersonRecord} representing the merged result
     */
    public PersonValueObject merge(PersonValueObject other) {
        if (other == null) return this;
        String mergedName = other.name != null ? other.name : this.name;
        String mergedEmail = other.email != null ? other.email : this.email;
        int mergedAge = other.age >= 0 ? other.age : this.age; // treat negative age as "absent"
        return new PersonValueObject(this.id, mergedName, mergedEmail, mergedAge);
    }
}
