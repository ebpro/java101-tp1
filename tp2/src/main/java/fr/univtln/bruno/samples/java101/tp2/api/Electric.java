package fr.univtln.bruno.samples.java101.tp2.api;

/**
 * Marker interface for electric vehicles.
 * Used to illustrate interface-based multiple inheritance.
 */
public interface Electric {
    /**
     * Charges the vehicle's battery.
     * @return a short status label for the charging action.
     */
    String charge();
}
