package fr.univtln.bruno.samples.java101.tp2;

import fr.univtln.bruno.samples.java101.tp2.impl.Car;
import fr.univtln.bruno.samples.java101.tp2.impl.ElectricCar;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResourceStateTest {

    @Test
    void carFuelConsumptionAndRefuel() {
        Car car = new Car("F1");
        // default full tank
        assertThat(car.getFuelLevel()).isEqualTo(100.0);

        // moving consumes 5.0
        String action = car.move();
        assertThat(action).isEqualTo("drives");
        assertThat(car.getFuelLevel()).isEqualTo(95.0);

        // refuel to 50%
        car.refuel(50.0);
        assertThat(car.getFuelLevel()).isEqualTo(50.0);

        // refuel beyond 100 is clamped
        car.refuel(150.0);
        assertThat(car.getFuelLevel()).isEqualTo(100.0);
    }

    @Test
    void electricCarBatteryConsumptionAndCharge() {
        ElectricCar ec = new ElectricCar("B1");
        assertThat(ec.getBatteryLevel()).isEqualTo(100.0);

        // moving consumes 8.0
        String action = ec.move();
        assertThat(action).isEqualTo("silently drives");
        assertThat(ec.getBatteryLevel()).isEqualTo(92.0);

        // charge resets to full
        String charge = ec.charge();
        assertThat(charge).isEqualTo("charging");
        assertThat(ec.getBatteryLevel()).isEqualTo(100.0);
    }
}

