package fr.univtln.bruno.samples.java101.tp1.factory;

/**
 * Deprecated compatibility wrapper for the new {@link PersonWithFactory} class.
 *
 * <p>Keep this thin wrapper while callers migrate to {@code PersonWithFactory}.
 * It delegates to {@link PersonWithFactory} to preserve the original API.</p>
 */
@Deprecated
public final class PersonFactory {

    private PersonFactory() { }

    /**
     * Create a person with the provided attributes.
     *
     * @param firstName first name (not null)
     * @param lastName last name (not null)
     * @param age age in years
     * @return a {@link PersonWithFactory} instance
     */
    public static PersonWithFactory of(String firstName, String lastName, int age) {
        return PersonWithFactory.of(firstName, lastName, age);
    }

    /**
     * Default example person (used for demos/tests).
     *
     * @return a default {@link PersonWithFactory}
     */
    public static PersonWithFactory defaultPerson() {
        return PersonWithFactory.defaultPerson();
    }

    /**
     * Convenience factory returning a teenager example.
     *
     * @param firstName first name (not null)
     * @param lastName last name (not null)
     * @return a teen {@link PersonWithFactory}
     */
    public static PersonWithFactory teenager(String firstName, String lastName) {
        return PersonWithFactory.teenager(firstName, lastName);
    }

}
