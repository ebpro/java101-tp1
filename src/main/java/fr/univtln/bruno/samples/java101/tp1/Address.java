package fr.univtln.bruno.samples.java101.tp1;

import java.util.Objects;

/**
 * Immutable address value object used in examples.
 *
 * <p>This class provides multiple overloaded constructors to show the classic
 * approach of constructor overloading (useful for small examples).
 *   It is on purpose to demonstrate the limitation of using multiple
 *  overloaded constructors when optional parameters or many variants are required. The
 *  constructor approach quickly becomes hard to read and maintain (the "telescoping"
 *  or constructor-explosion problem).
 * For flexible construction the nested {@link fr.univtln.bruno.samples.java101.tp1.builder.AddressWithBuilder}
 * should be preferred when there are many optional fields.</p>
 *
 * <p>Notes:
 * <ul>
 *   <li>None of the fields are allowed to be {@code null} when constructing via the {@link fr.univtln.bruno.samples.java101.tp1.builder.AddressWithBuilder};
 *       the overloaded constructors normalize nulls to empty strings.</li>
 *   <li>The class is immutable: fields are final and no setters are provided.</li>
 * </ul>
 */
public final class Address {
    private final String street;
    private final String city;
    private final String zipCode;

    // Overloaded constructors
    /**
     * Create an empty Address (all fields empty).
     */
    public Address() {
        this("", "", "");
    }

    /**
     * Create an Address with street only.
     * @param street street name (null is normalized to empty string)
     */
    public Address(String street) {
        this(street, "", "");
    }

    /**
     * Create an Address with street and city.
     * @param street street name (null is normalized to empty string)
     * @param city city name (null is normalized to empty string)
     */
    public Address(String street, String city) {
        this(street, city, "");
    }

    /**
     * Full constructor.
     *
     * @param street  the street (null normalized to empty string)
     * @param city    the city (null normalized to empty string)
     * @param zipCode the zip code (null normalized to empty string)
     */
    public Address(String street, String city, String zipCode) {
        this.street = street == null ? "" : street;
        this.city = city == null ? "" : city;
        this.zipCode = zipCode == null ? "" : zipCode;
    }

    /**
     * Returns the street name.
     * @return street (never null)
     */
    public String street() { return street; }

    /**
     * Returns the city name.
     * @return city (never null)
     */
    public String city() { return city; }

    /**
     * Returns the zip code.
     * @return zip code (never null)
     */
    public String zipCode() { return zipCode; }

    @Override
    public String toString() {
        return street + ", " + zipCode + " " + city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) && Objects.equals(city, address.city) && Objects.equals(zipCode, address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, zipCode);
    }

}
