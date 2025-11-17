package fr.univtln.bruno.samples.java101.tp2.impl;

import fr.univtln.bruno.samples.java101.tp2.api.Drivable;
import fr.univtln.bruno.samples.java101.tp2.api.Electric;
import fr.univtln.bruno.samples.java101.tp2.api.Rechargeable;
import fr.univtln.bruno.samples.java101.tp2.model.Vehicle;

/**
 * Electric bike: lightweight electric vehicle.
 * Demonstrates that electric vehicles don't need to be motor vehicles
 * (no inheritance from MotorVehicle, directly from Vehicle).
 */
public class ElectricBike extends Vehicle implements Drivable, Electric, Rechargeable {
    private double batteryLevel; // percentage 0..100

    public ElectricBike(String id) {
        super(id);
        this.batteryLevel = 100.0;
    }

    public ElectricBike(String id, String make, String model, int year) {
        super(id, make, model, year);
        this.batteryLevel = 100.0;
    }

    /** {@inheritDoc} */
    @Override
    public String move() {
        if (batteryLevel <= 0) return "pedals manually (no battery)";
        batteryLevel = Math.max(0.0, batteryLevel - 3.0);
        return "pedals with electric assist";
    }

    /** {@inheritDoc} */
    @Override
    public String drive() {
        return move();
    }

    /** {@inheritDoc} */
    @Override
    public String start() {
        if (batteryLevel <= 0) return "battery empty, manual mode";
        return "electric assist activated";
    }

    /** {@inheritDoc} */
    @Override
    public String charge() {
        this.batteryLevel = 100.0;
        return "charging";
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
     * @return the category label for electric bikes.
     */
    public static String category() {
        return "ElectricBike";
    }

    /**
     * Covariant copy: returns an ElectricBike instance.
     * @return a new ElectricBike with the same identifier.
     */
    @Override
    public ElectricBike copy() {
        ElectricBike eb = new ElectricBike(this.getId(), this.make, this.model, this.year);
        eb.batteryLevel = this.batteryLevel;
        return eb;
    }
}

