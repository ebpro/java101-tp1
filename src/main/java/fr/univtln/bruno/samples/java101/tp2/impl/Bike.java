package fr.univtln.bruno.samples.java101.tp2.impl;

import fr.univtln.bruno.samples.java101.tp2.api.Drivable;
import fr.univtln.bruno.samples.java101.tp2.model.Vehicle;

/**
 * Lightweight Vehicle implementation representing a bike.
 */
public class Bike extends Vehicle implements Drivable {

    public Bike(String id) {
        super(id);
    }

    /** {@inheritDoc} */
    @Override
    public String move() {
        return "pedals";
    }

    /** {@inheritDoc} */
    @Override
    public String drive() {
        return move();
    }

    /**
     * Static category used to demonstrate method hiding.
     * @return the category label for bikes.
     */
    public static String category() {
        return "Bike";
    }

    /**
     * Covariant copy: returns a Bike instance.
     * @return a new Bike with the same identifier.
     */
    @Override
    public Bike copy() {
        return new Bike(this.id);
    }
}
