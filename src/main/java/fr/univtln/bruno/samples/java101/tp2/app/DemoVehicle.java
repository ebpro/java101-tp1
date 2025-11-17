package fr.univtln.bruno.samples.java101.tp2.app;

import fr.univtln.bruno.samples.java101.tp2.impl.Car;
import fr.univtln.bruno.samples.java101.tp2.impl.ElectricCar;
import fr.univtln.bruno.samples.java101.tp2.impl.ServiceCar;
import fr.univtln.bruno.samples.java101.tp2.model.Vehicle;
import fr.univtln.bruno.samples.java101.tp2.service.Driver;
import fr.univtln.bruno.samples.java101.tp2.service.Manufacturer;

/**
 * Small post-lecture demo showcasing:
 * - inheritance (Vehicle -> Car/Bike/ElectricCar)
 * - abstract classes and partial implementations (Vehicle.description())
 * - interfaces (+ default methods) and multiple inheritance (Drivable, Electric, Maintainable)
 * - delegation (Driver -> Drivable)
 * - covariant returns (copy())
 * - static method hiding (category())
 */
public final class DemoVehicle {
    private DemoVehicle() {}

    public static void main(String[] args) {
        System.out.println("== Polymorphisme ==");
        Vehicle v1 = new Car("C1");
        Vehicle v2 = new ElectricCar("E1");
        System.out.println("v1.move() => " + v1.move());
        System.out.println("v2.description() => " + v2.description());

        System.out.println("\n== Method hiding (static) ==");
        System.out.println("Vehicle.category() => " + Vehicle.category());
        System.out.println("Car.category() => " + Car.category());
        System.out.println("ElectricCar.category() => " + ElectricCar.category());

        System.out.println("\n== Covariant returns (copy) ==");
        Car c = new Car("C2");
        Car cCopy = c.copy();
        System.out.println("Car.copy() => " + cCopy);
        ElectricCar ec = new ElectricCar("E2");
        ElectricCar ecCopy = ec.copy();
        System.out.println("ElectricCar.copy() => " + ecCopy);

        System.out.println("\n== Délégation ==");
        ServiceCar svc = new ServiceCar("SVC-1");
        Driver alice = new Driver("Alice", svc);
        System.out.println("Driver.startVehicle() => " + alice.startVehicle());
        System.out.println("Driver.drive() => " + alice.drive());

        System.out.println("\n== Composition (fabrication) ==");
        Manufacturer maker = new Manufacturer("Acme");
        Vehicle prod = maker.produce("AC-1");
        System.out.println("Manufacturer.produce => " + prod);

        System.out.println("\n== instanceof / cast ==");
        if (v2 instanceof ElectricCar ecar) {
            System.out.println("Downcast ok => charge() => " + ecar.charge());
        }

        System.out.println("\nDone.");
    }
}
