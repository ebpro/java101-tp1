package fr.univtln.bruno.samples.java101.tp1.builder;

import java.util.Objects;

/**
 * Builder-based immutable Address variant.
 *
 * <p>This class demonstrates the classical Builder pattern: the target type is
 * immutable and constructed from a mutable Builder object, which allows
 * readable, named construction of complex objects.</p>
 */
public final class AddressWithBuilder {
    private final String street;
    private final String city;
    private final String zipCode;

    /**
     * Private constructor used by the Builder.
     * @param b the builder
     */
    private AddressWithBuilder(Builder b) {
        this.street = b.street;
        this.city = b.city;
        this.zipCode = b.zipCode;
    }

    // Getters used to access fields. Note that no setters are provided: the class is immutable.
    /**
     * Returns the street name.
     *
     * @return the street (never null)
     */
    public String street() { return street; }

    /**
     * Returns the city name.
     *
     * @return the city (never null)
     */
    public String city() { return city; }

    /**
     * Returns the zip code.
     *
     * @return the zip code (never null)
     */
    public String zipCode() { return zipCode; }

    @Override
    public String toString() {
        return street + ", " + zipCode + " " + city;
    }

    /**
     * Creates a new Builder instance for AddressWithBuilder.
     * @return a new Builder
     */
    public static Builder builder() { return new Builder(); }

    /**
     * Builder class for AddressWithBuilder.
     * <p>The Builder allows setting individual attributes via
     * named methods and then constructing the immutable AddressWithBuilder
     * instance via the {@link #build()} method.</p>
     */
    public static final class Builder {
        private String street = "";
        private String city = "";
        private String zipCode = "";

        /**
         * Public no-arg constructor for the builder; documented to satisfy Javadoc.
         */
        public Builder() {
            // intentionally empty; builder fields are initialized with defaults above
        }

        /**
         * Sets the street for the Address being built.
         * @param s the street
         * @return this builder for chaining
         */
        public Builder street(String s) { this.street = s; return this; }

        /**
         * Sets the city for the Address being built.
         * @param c the city
         * @return this builder for chaining
         */
        public Builder city(String c) { this.city = c; return this; }

        /**
         * Sets the zip code for the Address being built.
         * @param z the zip code
         * @return this builder for chaining
         */
        public Builder zipCode(String z) { this.zipCode = z; return this; }

        /**
         * Builds the AddressWithBuilder instance.
         * @return the constructed AddressWithBuilder
         * @throws NullPointerException if any required attribute (street, city, zipCode) is null
         */
        public AddressWithBuilder build() {
            Objects.requireNonNull(street, "street required");
            Objects.requireNonNull(city, "city required");
            Objects.requireNonNull(zipCode, "zipCode required");

            return new AddressWithBuilder(this);
        }
    }
}
