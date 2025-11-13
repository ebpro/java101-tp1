package fr.univtln.bruno.samples.java101.tp1.factory;

import fr.univtln.bruno.samples.java101.tp1.Person;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for {@link PersonFactory}.
 *
 * <p>Verifies that named factory methods return instances with sensible defaults
 * and that intent is clearer than using many overloaded constructors.</p>
 */
class PersonFactoryTest {

    /**
     * The default factory should create a person with the configured defaults.
     */
    @Test
    void factoryCreatesDefaultPerson() {
        Person p = PersonFactory.defaultPerson();
        assertThat(p.getFirstName()).isEqualTo("John");
        assertThat(p.getAge()).isEqualTo(30);
    }

    /**
     * The createTeenager factory returns a person in the teenager range.
     */
    @Test
    void createTeenager() {
        Person t = PersonFactory.teenager("Anna", "B");
        assertThat(t.getAge()).isBetween(13, 19);
        assertThat(t.getFirstName()).isEqualTo("Anna");
    }
}
