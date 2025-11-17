package fr.univtln.bruno.samples.java101.tp2;

import fr.univtln.bruno.samples.java101.tp2.impl.Car;
import fr.univtln.bruno.samples.java101.tp2.impl.ElectricCar;
import fr.univtln.bruno.samples.java101.tp2.model.Vehicle;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class PolymorphismTest {

    @Test
    void runtimePolymorphismAndSafeDowncast() {
        Vehicle v = new ElectricCar("E-100");
        // runtime polymorphism: impl spécifique d'ElectricCar
        assertThat(v.move()).isEqualTo("silently drives");
        assertThat(v).isInstanceOf(ElectricCar.class);

        ElectricCar ec = (ElectricCar) v; // safe cast
        assertThat(ec.charge()).isEqualTo("charging");
    }

    @Test
    void unsafeDowncastThrowsClassCastException() {
        Vehicle car = new Car("C-1");
        assertThatThrownBy(() -> {
            ElectricCar e = (ElectricCar) car; // unsafe
        }).isInstanceOf(ClassCastException.class);
    }
}
