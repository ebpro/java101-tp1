package fr.univtln.bruno.samples.java101.tp1.immutable;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for {@link PersonValueObject} record showing immutability,
 * validation, factory methods, and non-mutating update helpers.
 */
class PersonValueObjectTest {

    /**
     * Record canonical constructor should create an instance with all fields set.
     */
    @Test
    void canonicalConstructorCreatesInstance() {
        PersonValueObject p = new PersonValueObject("id1", "Alice", "alice@example.com", 30);
        assertThat(p.id()).isEqualTo("id1");
        assertThat(p.name()).isEqualTo("Alice");
        assertThat(p.email()).isEqualTo("alice@example.com");
        assertThat(p.age()).isEqualTo(30);
    }

    /**
     * Factory method {@code of(...)} should create a valid instance.
     */
    @Test
    void factoryMethodCreatesInstance() {
        PersonValueObject p = PersonValueObject.of("id2", "Bob", "bob@example.com", 25);
        assertThat(p.id()).isEqualTo("id2");
        assertThat(p.name()).isEqualTo("Bob");
        assertThat(p.email()).isEqualTo("bob@example.com");
        assertThat(p.age()).isEqualTo(25);
    }

    /**
     * Constructor must reject null id with NullPointerException.
     */
    @Test
    void nullIdThrows() {
        assertThatThrownBy(() -> new PersonValueObject(null, "Alice", "alice@example.com", 30))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("id");
    }

    /**
     * Constructor must reject null name with NullPointerException.
     */
    @Test
    void nullNameThrows() {
        assertThatThrownBy(() -> new PersonValueObject("id1", null, "alice@example.com", 30))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("name");
    }

    /**
     * Constructor must reject null email with NullPointerException.
     */
    @Test
    void nullEmailThrows() {
        assertThatThrownBy(() -> new PersonValueObject("id1", "Alice", null, 30))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("email");
    }

    /**
     * Constructor must reject negative age with IllegalArgumentException.
     */
    @Test
    void negativeAgeThrows() {
        assertThatThrownBy(() -> new PersonValueObject("id1", "Alice", "alice@example.com", -1))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("age");
    }

    /**
     * {@code withName} returns a new instance with updated name, original unchanged.
     */
    @Test
    void withNameReturnsNewInstance() {
        PersonValueObject original = PersonValueObject.of("id1", "Alice", "alice@example.com", 30);
        PersonValueObject updated = original.withName("Alice Updated");

        assertThat(original.name()).isEqualTo("Alice"); // original unchanged
        assertThat(updated.name()).isEqualTo("Alice Updated");
        assertThat(updated.id()).isEqualTo(original.id());
        assertThat(updated.email()).isEqualTo(original.email());
        assertThat(updated.age()).isEqualTo(original.age());
    }

    /**
     * {@code withEmail} returns a new instance with updated email, original unchanged.
     */
    @Test
    void withEmailReturnsNewInstance() {
        PersonValueObject original = PersonValueObject.of("id1", "Alice", "alice@example.com", 30);
        PersonValueObject updated = original.withEmail("newemail@example.com");

        assertThat(original.email()).isEqualTo("alice@example.com"); // original unchanged
        assertThat(updated.email()).isEqualTo("newemail@example.com");
        assertThat(updated.id()).isEqualTo(original.id());
        assertThat(updated.name()).isEqualTo(original.name());
        assertThat(updated.age()).isEqualTo(original.age());
    }

    /**
     * {@code withAge} returns a new instance with updated age, original unchanged.
     */
    @Test
    void withAgeReturnsNewInstance() {
        PersonValueObject original = PersonValueObject.of("id1", "Alice", "alice@example.com", 30);
        PersonValueObject updated = original.withAge(31);

        assertThat(original.age()).isEqualTo(30); // original unchanged
        assertThat(updated.age()).isEqualTo(31);
        assertThat(updated.id()).isEqualTo(original.id());
        assertThat(updated.name()).isEqualTo(original.name());
        assertThat(updated.email()).isEqualTo(original.email());
    }

    /**
     * {@code incrementAge} returns a new instance with age incremented by one.
     */
    @Test
    void incrementAgeReturnsNewInstance() {
        PersonValueObject original = PersonValueObject.of("id1", "Alice", "alice@example.com", 30);
        PersonValueObject incremented = original.incrementAge();

        assertThat(original.age()).isEqualTo(30); // original unchanged
        assertThat(incremented.age()).isEqualTo(31);
    }

    /**
     * {@code merge} with null argument returns the original instance unchanged.
     */
    @Test
    void mergeWithNullReturnsOriginal() {
        PersonValueObject original = PersonValueObject.of("id1", "Alice", "alice@example.com", 30);
        PersonValueObject merged = original.merge(null);

        assertThat(merged).isSameAs(original);
    }

    /**
     * {@code merge} replaces non-null fields from the other instance.
     * Note: ID is NOT merged (it's the entity identity and remains from base).
     */
    @Test
    void mergeReplacesNonNullFields() {
        PersonValueObject base = PersonValueObject.of("id1", "Alice", "alice@example.com", 30);
        PersonValueObject other = PersonValueObject.of("id2", "Bob", "bob@example.com", 25);

        PersonValueObject merged = base.merge(other);

        // ID stays from base (entity identity is preserved)
        assertThat(merged.id()).isEqualTo("id1");
        // Other non-null fields from 'other' override 'base'
        assertThat(merged.name()).isEqualTo("Bob");
        assertThat(merged.email()).isEqualTo("bob@example.com");
        assertThat(merged.age()).isEqualTo(25);
    }

    /**
     * Two instances with identical fields should be equal (record equality).
     */
    @Test
    void recordEqualityWorks() {
        PersonValueObject p1 = PersonValueObject.of("id1", "Alice", "alice@example.com", 30);
        PersonValueObject p2 = PersonValueObject.of("id1", "Alice", "alice@example.com", 30);

        assertThat(p1).isEqualTo(p2);
        assertThat(p1.hashCode()).isEqualTo(p2.hashCode());
    }

    /**
     * {@code toString} should include all record components.
     */
    @Test
    void toStringIncludesAllComponents() {
        PersonValueObject p = PersonValueObject.of("id1", "Alice", "alice@example.com", 30);
        String str = p.toString();

        assertThat(str).contains("id1");
        assertThat(str).contains("Alice");
        assertThat(str).contains("alice@example.com");
        assertThat(str).contains("30");
    }
}

