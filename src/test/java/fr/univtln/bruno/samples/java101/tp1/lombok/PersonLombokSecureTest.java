package fr.univtln.bruno.samples.java101.tp1.lombok;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * Tests for {@link PersonLombokSecure} to ensure validation is applied by
 * both the builder and the factory.
 */
class PersonLombokSecureTest {

    @Test
    void builderRejectsNegativeAge() {
        assertThatThrownBy(() -> PersonLombokSecure.builder()
                .firstName("A")
                .lastName("B")
                .age(-1)
                .build())
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("age");
    }

    @Test
    void factoryRejectsNegativeAge() {
        assertThatThrownBy(() -> PersonLombokSecure.of("A", "B", -1))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("age");
    }
}

