package fr.univtln.bruno.samples.java101.tp2.api;

/**
 * Contract for vehicles that can be refueled.
 * Demonstrates Interface Segregation Principle: small, focused interfaces.
 */
public interface Refuelable {
    /**
     * Refuel the vehicle to the specified percentage.
     * Implementations should clamp the value to [0..100].
     * @param percent target fuel level (0..100)
     */
    void refuel(double percent);

    /**
     * Returns the current fuel level.
     * @return fuel level as percentage (0..100)
     */
    double getFuelLevel();
}

