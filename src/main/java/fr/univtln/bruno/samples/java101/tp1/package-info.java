/**
 * Example package for the Java 101 practical exercises (TP1).
 *
 * <p>This package contains small, self-contained examples used in the course
 * to illustrate core Java concepts and common design choices. The goal is to
 * provide readable, minimal code that students can run, inspect and modify.
 * The examples deliberately show different approaches to
 * modeling simple domain data (mutable POJO, immutable class, builder, Lombok,
 * in other packages) and how construction choices
 * affect API ergonomics and safety.</p>
 *
 * <p>Key classes found in this package:
 * <ul>
 *   <li>{@link fr.univtln.bruno.samples.java101.tp1.Person} — a mutable example used to show basic
 *       state, setters/getters, validation and simple invariants.</li>
 *   <li>{@link fr.univtln.bruno.samples.java101.tp1.Address} — an immutable value object with
 *       overloaded constructors and a nested {@code Builder} to illustrate different
 *       construction styles.</li>
 *   <li>{@link fr.univtln.bruno.samples.java101.tp1.factory.PersonFactory} — small factory methods
 *       demonstrating named constructors to avoid constructor explosion.</li>
 *   <li>{@link fr.univtln.bruno.samples.java101.tp1.immutable.PersonImmutable} — an example of an
 *       immutable class implemented without records (final fields, builder).</li>
 *   <li>{@link fr.univtln.bruno.samples.java101.tp1.lombok.PersonLombok} — a compact example
 *       using Lombok annotations to reduce boilerplate (requires annotation processing).</li>
 *   <li>{@link fr.univtln.bruno.samples.java101.tp1.Demo} — an executable demo that exercises
 *       the sample classes and logs output to show typical usage.</li>
 * </ul>
 *
 * <p>Pedagogical notes:
 * <ul>
 *   <li>The samples are intentionally simple and sometimes duplicate concepts to make
 *       exploration easy in exercises.</li>
 *   <li>Prefer the immutable variants and builder patterns when teaching robustness and
 *       thread-safety, but start with mutable examples to illustrate state and aliasing.</li>
 *   <li>Some examples rely on external tooling (Lombok) — see the project README and
 *       the Maven configuration for setup instructions.</li>
 * </ul>
 */
package fr.univtln.bruno.samples.java101.tp1;
