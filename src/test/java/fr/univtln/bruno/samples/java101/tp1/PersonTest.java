package fr.univtln.bruno.samples.java101.tp1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for {@link Person} (mutable example used in TP1).
 *
 * <p>These tests validate object construction, basic behavior of {@link Person#fullName}
 * and enforcement of the age invariant (age >= 0).</p>
 */
class PersonTest {

    /**
     * Ensures the fullName() method composes first and last name and that the age is stored correctly.
     */
    @Test
    void recordValidationAndFullName() {
        Person p = new Person("John","Doe",20);
        assertThat(p.fullName()).isEqualTo("John Doe");
        assertThat(p.getAge()).isEqualTo(20);
    }

    /**
     * Negative ages are invalid and should cause an IllegalArgumentException.
     */
    @Test
    void negativeAgeThrows() {
        assertThatThrownBy(() -> new Person("X","Y", -1)).isInstanceOf(IllegalArgumentException.class);
    }
}
