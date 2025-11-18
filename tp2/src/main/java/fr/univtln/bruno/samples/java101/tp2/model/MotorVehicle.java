package fr.univtln.bruno.samples.java101.tp2.model;

/**
 * Abstract motor vehicle: vehicles with an engine/motor.
 * Intermediate abstraction between Vehicle and concrete powered implementations.
 * Adds common behavior for motor-powered vehicles.
 */
public abstract class MotorVehicle extends Vehicle {

    protected MotorVehicle(String id) {
        super(id);
    }

    protected MotorVehicle(String id, String make, String model, int year) {
        super(id, make, model, year);
    }

    /**
     * Returns technical specifications about the motor/engine.
     * Each implementation provides its own power source description.
     * @return power source information
     */
    public abstract String getPowerSource();
}

