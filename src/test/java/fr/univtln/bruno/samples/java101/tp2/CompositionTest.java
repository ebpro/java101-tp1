package fr.univtln.bruno.samples.java101.tp2;

import fr.univtln.bruno.samples.java101.tp2.impl.Car;
import fr.univtln.bruno.samples.java101.tp2.model.Vehicle;
import fr.univtln.bruno.samples.java101.tp2.service.Manufacturer;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CompositionTest {

    @Test
    void manufacturerProducesVehicle() {
        Manufacturer m = new Manufacturer("Acme");
        Vehicle v = m.produce("AC-1");
        assertThat(v).isInstanceOf(Car.class);
        assertThat(v.toString()).contains("AC-1");
    }
}
