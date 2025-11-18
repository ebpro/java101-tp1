package fr.univtln.bruno.samples.java101.tp2;

import fr.univtln.bruno.samples.java101.tp2.api.Rechargeable;
import fr.univtln.bruno.samples.java101.tp2.api.Refuelable;
import fr.univtln.bruno.samples.java101.tp2.impl.Car;
import fr.univtln.bruno.samples.java101.tp2.impl.ElectricBike;
import fr.univtln.bruno.samples.java101.tp2.impl.ElectricCar;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests demonstrating Interface Segregation Principle (ISP):
 * - Small, focused interfaces (Refuelable, Rechargeable)
 * - Polymorphic usage via interface references
 * - Different implementations can share common contracts
 */
public class InterfaceSegregationTest {

    @Test
    void refuelablePolymorphism() {
        // Only Car is Refuelable now (ElectricCar is not)
        Refuelable car = new Car("R1");

        // Polymorphic refuel
        car.refuel(60.0);

        assertThat(car.getFuelLevel()).isEqualTo(60.0);
    }

    @Test
    void rechargeablePolymorphism() {
        // ElectricCar and ElectricBike are Rechargeable
        Rechargeable ec = new ElectricCar("RC1");
        Rechargeable eb = new ElectricBike("RC2");

        assertThat(ec.getBatteryLevel()).isEqualTo(100.0);
        assertThat(eb.getBatteryLevel()).isEqualTo(100.0);

        // Simulate battery drain by calling move via concrete type
        ((ElectricCar) ec).move();
        ((ElectricBike) eb).move();
        assertThat(ec.getBatteryLevel()).isEqualTo(92.0);
        assertThat(eb.getBatteryLevel()).isEqualTo(97.0);

        // Polymorphic charge
        String result1 = ec.charge();
        String result2 = eb.charge();
        assertThat(result1).isEqualTo("charging");
        assertThat(result2).isEqualTo("charging");
        assertThat(ec.getBatteryLevel()).isEqualTo(100.0);
        assertThat(eb.getBatteryLevel()).isEqualTo(100.0);
    }

    @Test
    void mixedRefuelableCollection() {
        // Demonstrate collection of Refuelable vehicles (only fuel-based cars now)
        List<Refuelable> fleet = List.of(
            new Car("FLEET-1"),
            new Car("FLEET-2", "Toyota", "Corolla", 2020)
        );

        // Refuel all vehicles to 75%
        fleet.forEach(v -> v.refuel(75.0));

        // Verify all have 75% fuel
        assertThat(fleet).allMatch(v -> v.getFuelLevel() == 75.0);
    }

    @Test
    void interfaceSegregationBenefit() {
        // ISP benefit: code that only needs refueling doesn't depend on Electric, Drivable, etc.
        Refuelable vehicle = new Car("ISP-1");

        // This method only sees the Refuelable contract
        ensureMinimumFuel(vehicle, 50.0);

        assertThat(vehicle.getFuelLevel()).isGreaterThanOrEqualTo(50.0);
    }

    /**
     * Helper that demonstrates ISP: depends only on Refuelable, not on concrete types.
     */
    private void ensureMinimumFuel(Refuelable vehicle, double minLevel) {
        if (vehicle.getFuelLevel() < minLevel) {
            vehicle.refuel(minLevel);
        }
    }
}
