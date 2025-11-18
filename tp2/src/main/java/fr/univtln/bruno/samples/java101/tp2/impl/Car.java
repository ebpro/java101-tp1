package fr.univtln.bruno.samples.java101.tp2.impl;

import fr.univtln.bruno.samples.java101.tp2.api.Drivable;
import fr.univtln.bruno.samples.java101.tp2.api.Refuelable;
import fr.univtln.bruno.samples.java101.tp2.model.MotorVehicle;

/**
 * Concrete Vehicle implementation representing a car.
 * Designed to stay small and focused: minimal state and behaviour.
 */
public class Car extends MotorVehicle implements Drivable, Refuelable {

    private double fuelLevel; // percentage 0.0 .. 100.0

    public Car(String id) {
        super(id);
        this.fuelLevel = 100.0; // default full tank for demo
    }

    public Car(String id, String make, String model, int year) {
        super(id, make, model, year);
        this.fuelLevel = 100.0;
    }

    /** {@inheritDoc} */
    @Override
    public String move() {
        if (fuelLevel <= 0) return "cannot move (no fuel)";
        // simulate fuel consumption
        fuelLevel = Math.max(0.0, fuelLevel - 5.0);
        return "drives";
    }

    /** {@inheritDoc} */
    @Override
    public String drive() {
        return move();
    }

    /**
     * Returns the current fuel level as a percentage.
     * @return fuel level in percent.
     */
    public double getFuelLevel() {
        return fuelLevel;
    }

    /**
     * Refuel the car to the specified percentage (clamped 0..100).
     * @param percent new fuel level
     */
    public void refuel(double percent) {
        this.fuelLevel = Math.max(0.0, Math.min(100.0, percent));
    }

    /**
     * Provides the default Drivable start behaviour for cars.
     * @return the Drivable default starting label.
     */
    @Override
    public String start() {
        if (fuelLevel <= 0) return "cannot start (no fuel)";
        return Drivable.super.start();
    }

    /**
     * Static category used to demonstrate method hiding.
     * @return the category label for cars.
     */
    public static String category() {
        return "Car";
    }

    /**
     * Covariant copy: returns a Car instance.
     * @return a new Car with the same identifier.
     */
    @Override
    public Car copy() {
        Car c = new Car(this.id, this.make, this.model, this.year);
        c.fuelLevel = this.fuelLevel;
        return c;
    }

    /** {@inheritDoc} */
    @Override
    public String getPowerSource() {
        return "Internal combustion engine (fuel)";
    }
}
