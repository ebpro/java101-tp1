package fr.univtln.bruno.samples.java101.tp1.immutable;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for {@link PersonImmutable} showing builder usage and constructor validation.
 */
class PersonImmutableTest {

    /**
     * The builder should construct an immutable PersonImmutable with correct fields.
     */
    @Test
    void builderCreatesImmutable() {
        PersonImmutable p = PersonImmutable.builder().firstName("X").lastName("Y").age(25).build();
        assertThat(p.getFirstName()).isEqualTo("X");
    }

    /**
     * Negative age must be rejected by the builder with IllegalArgumentException.
     */
    @Test
    void negativeAgeThrows() {
        assertThatThrownBy(() -> PersonImmutable.builder()
          .firstName("a")
          .lastName("b")
          .age(-1)
          .build())
            .isInstanceOf(IllegalArgumentException.class);
    }
}
