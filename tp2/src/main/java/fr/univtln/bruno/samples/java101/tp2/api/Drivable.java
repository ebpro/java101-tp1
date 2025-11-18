package fr.univtln.bruno.samples.java101.tp2.api;

/**
 * Drivable capability contract.
 * Demonstrates multiple inheritance via interfaces and a default method.
 */
public interface Drivable {
    /**
     * Performs the driving action for the underlying vehicle implementation.
     * @return an action verb describing the motion (e.g., "drives", "pedals").
     */
    String drive();

    /**
     * Default starting behaviour for drivable vehicles.
     * Implementations may override to refine the start sequence.
     * @return a small description of the start action.
     */
    default String start() {
        return "start engine"; // default; bikes may ignore
    }
}
