package fr.univtln.bruno.samples.java101.tp2;

import fr.univtln.bruno.samples.java101.tp2.impl.ServiceCar;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultMethodConflictTest {

    @Test
    void serviceCarResolvesDefaultMethodConflict() {
        ServiceCar sc = new ServiceCar("SVC-1");
        // start() should combine the two default implementations
        assertThat(sc.start()).contains("perform pre-check");
        assertThat(sc.start()).contains("start engine");
    }
}
