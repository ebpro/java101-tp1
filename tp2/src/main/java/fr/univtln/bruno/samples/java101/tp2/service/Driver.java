package fr.univtln.bruno.samples.java101.tp2.service;

import fr.univtln.bruno.samples.java101.tp2.api.Drivable;

/**
 * Simple service object illustrating delegation: the driver delegates
 * driving operations to a Drivable vehicle it holds.
 */
public class Driver {
    private final Drivable vehicle;
    private final String name;

    public Driver(String name, Drivable vehicle) {
        this.name = name;
        this.vehicle = vehicle;
    }

    /**
     * Delegates the drive call to the underlying Drivable vehicle.
     * @return the action verb from the vehicle's implementation.
     */
    public String drive() {
        return vehicle.drive();
    }

    /**
     * Delegates the start call to the underlying Drivable vehicle.
     * @return the default or overridden start label from the vehicle.
     */
    public String startVehicle() {
        return vehicle.start();
    }

    /**
     * @return the driver's name.
     */
    public String getName() {
        return name;
    }
}
