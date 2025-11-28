/**
 * TP4 - Functional Programming & Modern Java Features
 *
 * Module overview
 * ----------------
 * This module provides didactic examples covering Java functional programming concepts,
 * lambda expressions, method references, functional interfaces, Stream API advanced patterns,
 * and modern Java features introduced from Java 8 to Java 25.
 *
 * Structure (key packages and files)
 * ----------------------------------
 * - lambda/           Lambda expressions, method references, functional interfaces
 * - functionalinterfaces/  Standard and custom functional interfaces examples
 * - streams/          Advanced Stream API patterns, collectors, and operations
 * - optional/         Optional API best practices and anti-patterns
 * - immutability/     Immutable data structures and records
 * - patternmatching/  Pattern matching for switch, records, sealed types
 * - textblocks/       Text blocks and string processing
 * - misc/             Miscellaneous modern features (var, sealed classes, etc.)
 * - Person.java       Immutable model (record) used across examples
 * - Product.java      Model for functional examples
 * - Demo.java         Compact runner that invokes representative examples
 *
 * Prerequisites
 * -------------
 * - Java 21+ (module uses records, pattern matching, sealed types, and modern JDK APIs)
 * - Lombok enabled for annotation processing
 *
 * Lectures covered
 * ----------------
 * This module corresponds to the following lectures:
 * 1. Functional Programming (lambdas, method references, functional interfaces)
 * 2. Stream API (intermediate/terminal operations, collectors, parallel streams)
 * 3. Misc Modern Features (text blocks, pattern matching, sealed classes, records)
 *
 * Build & run (module-specific)
 * -----------------------------
 * From the repository root:
 *   ./mvnw -pl tp4 -am test
 * to execute tests. To run the Demo main:
 *   ./mvnw -pl tp4 -am exec:java -Dexec.mainClass=fr.univtln.bruno.samples.java101.tp4.Demo
 *
 * Or build a shaded jar:
 *   ./mvnw -pl tp4 -am package
 *   java -jar tp4/target/*-withdependencies.jar
 *
 * Pedagogical notes
 * -----------------
 * 1) Lambda vs method reference
 *    - Examples show both verbose lambda syntax and concise method references
 *    - Helps students understand the equivalence and when to use each
 *
 * 2) Stream operations
 *    - Demonstrates intermediate (map, filter, flatMap) vs terminal (collect, forEach) operations
 *    - Shows common pitfalls with parallel streams and stateful operations
 *
 * 3) Optional usage
 *    - Shows proper use of Optional (as return type, not parameter)
 *    - Demonstrates anti-patterns to avoid
 *
 * 4) Modern Java features
 *    - Pattern matching for instanceof and switch
 *    - Records and sealed types for domain modeling
 *    - Text blocks for multi-line strings
 *
 * @author Emmanuel Bruno
 * @version 0.0.1-SNAPSHOT
 * @since Java 21
 */
package fr.univtln.bruno.samples.java101.tp4;

