package fr.univtln.bruno.samples.java101.tp2.api;

/**
 * Contract for vehicles that can be recharged.
 * Symmetric to Refuelable for electric vehicles.
 */
public interface Rechargeable {
    /**
     * Recharge the vehicle's battery to full capacity.
     * @return status message describing the charging action
     */
    String charge();

    /**
     * Returns the current battery level.
     * @return battery level as percentage (0..100)
     */
    double getBatteryLevel();
}

