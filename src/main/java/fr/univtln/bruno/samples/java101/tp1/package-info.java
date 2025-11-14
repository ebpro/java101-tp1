/**
 * Java 101 - TP1 sample package.
 *
 * This package contains small, well-documented examples intended for students
 * learning Java fundamentals. The samples progress from simple mutable POJOs to
 * factories, builders, immutable objects and concise Lombok-based examples.
 * They are intentionally minimal so students can run, read and modify them
 * during guided exercises.
 *
 * Main concepts illustrated (recommended reading order):
 * <ol>
 *   <li>Mutable POJO (`Person`) — basic getters/setters and validation.</li>
 *   <li>Factory methods (`factory.PersonWithFactory`, `factory.PersonSingleton`) — named constructors and centralised creation.</li>
 *   <li>Builder pattern (`builder.AddressWithBuilder`) — fluent construction for many optional params.</li>
 *   <li>Immutable value objects (`Address`, `immutable.PersonImmutable`) — final fields + builder.</li>
 *   <li>Lombok examples (`lombok` package) — reducing boilerplate with annotations.</li>
 *   <li>Packaging, testing and site generation — Maven, Shade, JaCoCo and site reports.</li>
 * </ol>
 *
 * Practical notes:
 * - The project uses Maven and includes the Maven Wrapper for reproducible builds.
 * - Tests are located under <code>src/test/java</code> and use JUnit and AssertJ.
 * - Logging uses SLF4J with a Logback binding provided in <code>src/main/resources</code>.
 */
package fr.univtln.bruno.samples.java101.tp1;
