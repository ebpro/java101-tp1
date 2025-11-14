package fr.univtln.bruno.samples.java101.tp1.factory;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for the factory API. Verifies the simple immutable PersonWithFactory
 * produced by the factory methods.
 */
class PersonWithFactoryTest {

    /**
     * The default factory should create a person with the configured defaults.
     */
    @Test
    void factoryCreatesDefaultPerson() {
        PersonWithFactory p = PersonWithFactory.defaultPerson();
        assertThat(p.getFirstName()).isEqualTo("John");
        assertThat(p.getAge()).isEqualTo(30);
    }

    /**
     * The createTeenager factory returns a person in the teenager range.
     */
    @Test
    void createTeenager() {
        PersonWithFactory t = PersonWithFactory.teenager("Anna", "B");
        assertThat(t.getAge()).isBetween(13, 19);
        assertThat(t.getFirstName()).isEqualTo("Anna");
    }
}
