package fr.univtln.bruno.samples.java101.tp1.builder;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for {@link AddressWithBuilder} demonstrating the Builder pattern.
 *
 * <p>The tests show how the builder yields an immutable target object and that
 * named builder methods improve readability compared to telescoping constructors.</p>
 */
class AddressWithBuilderTest {

    /**
     * Simple happy-path test: the builder sets fields and produces an immutable AddressWithBuilder.
     */
    @Test
    void builderCreatesAddress() {
        AddressWithBuilder a = AddressWithBuilder.builder().street("123 Example St").city("Example City").zipCode("00000").build();
        assertThat(a.city()).isEqualTo("Example City");
        assertThat(a.zipCode()).isEqualTo("00000");
    }
}
