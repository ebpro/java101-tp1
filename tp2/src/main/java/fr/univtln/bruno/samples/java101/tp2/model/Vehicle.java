package fr.univtln.bruno.samples.java101.tp2.model;

/**
 * Abstract domain type for TP2 examples.
 * Encapsulates a common identity and provides a partial implementation
 * while leaving core behaviour to subclasses.
 */
public abstract class Vehicle {
    protected String id;
    protected String make; // optional manufacturer name
    protected String model; // optional model name
    protected int year; // optional year (0 if unknown)

    protected Vehicle(String id) {
        this(id, null, null, 0);
    }

    protected Vehicle(String id, String make, String model, int year) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    /**
     * Describes the main motion behaviour of the vehicle.
     * Implementations must provide a concrete action (e.g., "drives").
     * @return an action verb describing the motion.
     */
    public abstract String move();

    /**
     * Final accessor to illustrate that subclasses cannot override it.
     * @return the immutable identifier of this vehicle.
     */
    public final String getId() {
        return id;
    }

    /**
     * Static method used to demonstrate method hiding in subclasses.
     * @return the base category label.
     */
    public static String category() {
        return "Vehicle";
    }

    /**
     * Abstract factory-like copy that subclasses implement with covariant
     * return types (returning their own concrete type).
     * @return a new instance logically equivalent to this vehicle.
     */
    public abstract Vehicle copy();

    /**
     * Concrete method in the abstract base: shows dynamic dispatch by
     * calling the abstract move() from a shared template.
     * @return a human-readable description line.
     */
    public String description() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append("#").append(getId());
        if (make != null || model != null || year != 0) {
            sb.append(" [");
            if (make != null) sb.append(make);
            if (model != null) sb.append(" ").append(model);
            if (year != 0) sb.append(" ").append(year);
            sb.append("]");
        }
        sb.append(" moves: ").append(move());
        return sb.toString();
    }

    @Override
    public String toString() {
        if (make != null || model != null) {
            return getClass().getSimpleName() + "(" + id + " - " + (make == null ? "" : make) + (model == null ? "" : " " + model) + ")";
        }
        return getClass().getSimpleName() + "(" + id + ")";
    }
}
