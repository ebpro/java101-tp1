package fr.univtln.bruno.samples.java101.tp2.api;

/**
 * Maintenance capability contract used to demonstrate default-method conflicts
 * and their resolution when combined with other interfaces.
 */
public interface Maintainable {
    /**
     * Default pre-flight/pre-drive check sequence.
     * @return a short description of the maintenance step.
     */
    default String start() {
        return "perform pre-check";
    }
}
