package fr.univtln.bruno.samples.java101.tp2;

import fr.univtln.bruno.samples.java101.tp2.impl.Car;
import fr.univtln.bruno.samples.java101.tp2.service.Driver;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DelegationTest {

    @Test
    void driverDelegatesToVehicleDriveAndStart() {
        Car car = new Car("C-9");
        Driver driver = new Driver("Alice", car);

        assertThat(driver.getName()).isEqualTo("Alice");
        assertThat(driver.drive()).isEqualTo("drives");
        assertThat(driver.startVehicle()).isEqualTo("start engine");
    }
}

