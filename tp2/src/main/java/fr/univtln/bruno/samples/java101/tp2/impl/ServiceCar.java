package fr.univtln.bruno.samples.java101.tp2.impl;

import fr.univtln.bruno.samples.java101.tp2.api.Maintainable;

/**
 * Service car combining Car's drivable features with maintenance checks.
 * Demonstrates resolving default-method conflicts by explicitly selecting
 * both sequences and composing them.
 */
public class ServiceCar extends Car implements Maintainable {

    public ServiceCar(String id) {
        super(id);
    }

    /**
     * Resolves the default-method conflict by composing Maintainable and Car
     * behaviours in a defined order.
     * @return the composed start sequence description.
     */
    @Override
    public String start() {
        // call Maintainable default then Car's implementation (which delegates to Drivable)
        String m = Maintainable.super.start();
        String d = super.start();
        return m + " + " + d;
    }

    /**
     * Covariant copy: returns a ServiceCar instance.
     * @return a new ServiceCar with the same identifier.
     */
    @Override
    public ServiceCar copy() {
        return new ServiceCar(getId());
    }
}
