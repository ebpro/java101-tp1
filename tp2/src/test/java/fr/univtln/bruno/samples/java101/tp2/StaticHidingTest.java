package fr.univtln.bruno.samples.java101.tp2;

import fr.univtln.bruno.samples.java101.tp2.impl.Car;
import fr.univtln.bruno.samples.java101.tp2.impl.ElectricCar;
import fr.univtln.bruno.samples.java101.tp2.model.Vehicle;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StaticHidingTest {

    @Test
    void staticCategoryIsHiddenBySubclass() {
        assertThat(Vehicle.category()).isEqualTo("Vehicle");
        assertThat(Car.category()).isEqualTo("Car");
        assertThat(ElectricCar.category()).isEqualTo("ElectricCar");
    }
}

