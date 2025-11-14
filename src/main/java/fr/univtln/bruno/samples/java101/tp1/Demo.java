package fr.univtln.bruno.samples.java101.tp1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.univtln.bruno.samples.java101.tp1.factory.PersonWithFactory;
import fr.univtln.bruno.samples.java101.tp1.builder.AddressWithBuilder;

/**
 * Small demo application that exercises the sample classes from the lab.
 *
 * <p>The `main` method demonstrates creating `Person` instances (via the
 * dedicated `PersonFactory`), creating `Address` with overloaded constructors
 * and with an explicit Builder (`AddressWithBuilder`), and printing simple
 * information with a logger. The class is intended as a runnable example for
 * students to inspect and run.</p>
 *
 * <p>A Logger is a common way to handle output in real applications, as opposed
 * to using `System.out.println`. It allows more flexible control over logging
 * levels and output destinations.</p>
 * <p>Logging is done via SLF4J; ensure that a suitable binding (e.g., Logback, see pom.xml)
 * is present on the classpath to see the log output.</p>
 */
public class Demo {
    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    /**
     * Public no-argument constructor for Demo. The class only exposes a static main
     * method; this constructor exists to satisfy Javadoc conventions and for potential
     * instantiation in examples/tools.
     */
    public Demo() {
        // no-op constructor: documented and intentionally empty for examples/tools
    }

    /**
     * Run the demo. Prints a few example objects to the logger.
     *
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        // Create persons using the factory (preferred API)
        fr.univtln.bruno.samples.java101.tp1.factory.PersonWithFactory p1 = PersonWithFactory.defaultPerson();
        fr.univtln.bruno.samples.java101.tp1.factory.PersonWithFactory p2 = PersonWithFactory.teenager("Bob", "Martin");

        // Address via overloaded constructors (less flexible as options grow)
        Address a1 = new Address("1 Example St", "Example City", "00000");

        // Address via explicit Builder pattern
        AddressWithBuilder a2 = AddressWithBuilder.builder()
          .street("2 Example Ave")
          .city("Example City")
           .zipCode("06001")
           .build();

        // Print some info via the logger
        if (logger.isInfoEnabled()) {
            logger.info("Person1: {} {}", p1.getFirstName(), p1.getLastName());
            logger.info("Address (ctor): {}", a1);
            logger.info("Address (builder): {}", a2);
            logger.info("Second person: {} {} (age={})", p2.getFirstName(), p2.getLastName(), p2.getAge());
        }
    }
}
