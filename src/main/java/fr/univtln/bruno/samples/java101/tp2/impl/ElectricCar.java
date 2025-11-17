package fr.univtln.bruno.samples.java101.tp2.impl;

import fr.univtln.bruno.samples.java101.tp2.api.Electric;
import fr.univtln.bruno.samples.java101.tp2.api.Drivable;
import fr.univtln.bruno.samples.java101.tp2.api.Rechargeable;
import fr.univtln.bruno.samples.java101.tp2.model.MotorVehicle;

/**
 * Concrete Vehicle representing an electric car.
 * Uses battery instead of fuel - demonstrates separation of concerns via ISP.
 */
public class ElectricCar extends MotorVehicle implements Drivable, Electric, Rechargeable {
    private double batteryLevel; // percentage 0..100

    public ElectricCar(String id) {
        super(id);
        this.batteryLevel = 100.0;
    }

    public ElectricCar(String id, String make, String model, int year) {
        super(id, make, model, year);
        this.batteryLevel = 100.0;
    }

    /** {@inheritDoc} */
    @Override
    public String charge() {
        this.batteryLevel = 100.0;
        return "charging";
    }

    /** {@inheritDoc} */
    @Override
    public String move() {
        if (batteryLevel <= 0) return "cannot move (no battery)";
        batteryLevel = Math.max(0.0, batteryLevel - 8.0);
        return "silently drives";
    }

    /** {@inheritDoc} */
    @Override
    public String drive() {
        return move();
    }

    /** {@inheritDoc} */
    @Override
    public String start() {
        if (batteryLevel <= 0) return "cannot start (no battery)";
        return "electric system ready";
    }

    /**
     * Returns current battery level as a percentage.
     * @return battery level percent
     */
    public double getBatteryLevel() {
        return batteryLevel;
    }

    /**
     * Static category used to demonstrate method hiding.
     * @return the category label for electric cars.
     */
    public static String category() {
        return "ElectricCar";
    }

    /**
     * Covariant copy: returns an ElectricCar instance.
     * @return a new ElectricCar with the same identifier.
     */
    @Override
    public ElectricCar copy() {
        ElectricCar ec = new ElectricCar(this.getId(), this.make, this.model, this.year);
        ec.batteryLevel = this.batteryLevel;
        return ec;
    }

    /** {@inheritDoc} */
    @Override
    public String getPowerSource() {
        return "Electric motor (battery)";
    }
}
