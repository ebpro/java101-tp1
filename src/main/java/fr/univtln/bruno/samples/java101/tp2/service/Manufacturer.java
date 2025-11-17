package fr.univtln.bruno.samples.java101.tp2.service;

import fr.univtln.bruno.samples.java101.tp2.impl.Car;
import fr.univtln.bruno.samples.java101.tp2.model.Vehicle;

/**
 * Composition-centric example: a Manufacturer produces Vehicles.
 */
public class Manufacturer {
    private String name;

    public Manufacturer(String name) {
        this.name = name;
    }

    /**
     * Produces a new car instance with the given id.
     * In real code this could be a factory choosing implementations.
     * @param id the identifier to assign to the new vehicle
     * @return a Vehicle instance (Car) with the provided id
     */
    public Vehicle produce(String id) {
        return new Car(id);
    }
}
