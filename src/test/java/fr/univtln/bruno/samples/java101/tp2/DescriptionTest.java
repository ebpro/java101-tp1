package fr.univtln.bruno.samples.java101.tp2;

import fr.univtln.bruno.samples.java101.tp2.impl.Car;
import fr.univtln.bruno.samples.java101.tp2.impl.ElectricCar;
import fr.univtln.bruno.samples.java101.tp2.model.Vehicle;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DescriptionTest {

    @Test
    void descriptionUsesMoveFromSubclass() {
        Vehicle v1 = new Car("D-1");
        assertThat(v1.description()).isEqualTo("Car#D-1 moves: drives");

        Vehicle v2 = new ElectricCar("D-2");
        assertThat(v2.description()).isEqualTo("ElectricCar#D-2 moves: silently drives");
    }
}
