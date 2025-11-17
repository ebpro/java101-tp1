package fr.univtln.bruno.samples.java101.tp2;

import fr.univtln.bruno.samples.java101.tp2.impl.Bike;
import fr.univtln.bruno.samples.java101.tp2.impl.Car;
import fr.univtln.bruno.samples.java101.tp2.impl.ElectricCar;
import fr.univtln.bruno.samples.java101.tp2.model.Vehicle;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VehicleTest {

    @Test
    void carAndBikeMove() {
        Vehicle car = new Car("C1");
        Vehicle bike = new Bike("B1");

        assertThat(car.move()).isEqualTo("drives");
        assertThat(bike.move()).isEqualTo("pedals");
    }

    @Test
    void electricCarChargeAndMove() {
        ElectricCar ec = new ElectricCar("E1");
        assertThat(ec.charge()).isEqualTo("charging");
        assertThat(ec.move()).isEqualTo("silently drives");
    }
}
