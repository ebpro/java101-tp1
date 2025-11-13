package fr.univtln.bruno.samples.java101.tp1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for {@link Address}.
 *
 * <p>These tests verify the basic construction paths and the normalization
 * behaviour: the overloaded constructors should produce a valid immutable
 * Address and convert {@code null} inputs to empty strings (as an example of
 * defensive normalization used in the course).</p>
 */
class AddressTest {

    /**
     * Verifies that the three-arguments constructor sets fields correctly and
     * that {@link Address#toString} contains the zip code.
     */
    @Test
    void constructorCreatesAddress() {
        Address a = new Address("St", "City", "12345");
        assertThat(a.city()).isEqualTo("City");
        assertThat(a.toString()).contains("12345");
    }

    /**
     * The simple overloaded constructors normalize {@code null} values to empty
     * strings. This test ensures no exception is thrown and the normalization
     * is applied as documented in {@link Address}.
     */
    @Test
    void missingFieldsAreNormalized() {
        // The constructors normalize null values to empty strings (no exception expected)
        Address a = new Address(null, "C", "1");
        assertThat(a.street()).isEmpty();
        assertThat(a.city()).isEqualTo("C");
        assertThat(a.zipCode()).isEqualTo("1");
    }

    @Test
    void addressesAreEqualWhenFieldsMatch() {
        Address a1 = new Address("St", "City", "12345");
        Address a2 = new Address("St", "City", "12345");
        assertThat(a1).isEqualTo(a2);
        assertThat(a1.hashCode()).isEqualTo(a2.hashCode());
    }

    @Test
    void nullValuesAreNormalizedAndConsideredEqual() {
        Address a1 = new Address(null, "C", "1");
        Address a2 = new Address("", "C", "1");
        assertThat(a1).isEqualTo(a2);
    }
}
