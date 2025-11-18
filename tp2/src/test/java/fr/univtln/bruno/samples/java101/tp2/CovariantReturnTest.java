package fr.univtln.bruno.samples.java101.tp2;

import fr.univtln.bruno.samples.java101.tp2.impl.Car;
import fr.univtln.bruno.samples.java101.tp2.impl.ElectricCar;
import fr.univtln.bruno.samples.java101.tp2.model.Vehicle;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CovariantReturnTest {

    @Test
    void copyReturnsConcreteType() {
        Car c = new Car("CV-1");
        Vehicle c2 = c.copy();
        assertThat(c2).isInstanceOf(Car.class);

        ElectricCar e = new ElectricCar("EV-1");
        Vehicle e2 = e.copy();
        assertThat(e2).isInstanceOf(ElectricCar.class);
    }
}
