package fr.univtln.bruno.samples.java101.tp1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.univtln.bruno.samples.java101.tp1.builder.AddressWithBuilder;
import fr.univtln.bruno.samples.java101.tp1.factory.PersonWithFactory;
import fr.univtln.bruno.samples.java101.tp1.factory.PersonSingleton;

/**
 * Small demo application that exercises the sample classes from the lab.
 *
 * <p>The main method demonstrates creating instances using factory methods,
 * using the builder pattern, and showing the singleton usage example.
 * It is intentionally simple for students to run and inspect.</p>
 */
public class Demo {
    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    /**
     * Public no-argument constructor for Demo. Documented for clarity.
     */
    public Demo() {
        // intentionally empty
    }

    /**
     * Run the demo. Prints a few example objects to the logger.
     *
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        // Create persons using factory methods
        PersonWithFactory p1 = PersonWithFactory.defaultPerson();
        PersonWithFactory p2 = PersonWithFactory.teenager("Bob", "Martin");

        // Example: initialize singleton explicitly (optional)
        if (!PersonSingleton.isInitialized()) {
            PersonSingleton.initialize("Alice", "Smith", 28);
        }
        PersonSingleton singleton = PersonSingleton.getInstance();

        // Address via explicit Builder pattern
        AddressWithBuilder a2 = AddressWithBuilder.builder()
          .street("2 Example Ave")
          .city("Example City")
          .zipCode("06001")
          .build();

        // Print some info via the logger
        if (logger.isInfoEnabled()) {
            logger.info("Person1: {} {}", p1.getFirstName(), p1.getLastName());
            logger.info("Person2: {} {} (age={})", p2.getFirstName(), p2.getLastName(), p2.getAge());
            logger.info("Singleton person: {} (age={})", singleton.getFullName(), singleton.getAge());
            logger.info("Address (builder): {}", a2);
        }
    }
}
