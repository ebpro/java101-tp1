/**
 * TP3 - Collections & Streams
 *
 * Module overview
 * ----------------
 * This module provides didactic examples and small demos covering Java collections
 * (List, Set, Map, Queue, Deque), Comparator/Comparable patterns and the Streams API
 * (creation, intermediate operations, collectors). It also contains short notes on
 * best practices and common pitfalls.
 *
 * Structure (key packages and files)
 * ----------------------------------
 * - list/          Examples on List implementations and idioms
 * - set/           Examples on Set semantics and deduplication
 * - map/           Examples on Map usage and modern map methods
 * - queue/         Examples on Queue & Deque & PriorityQueue
 * - comparable/    Advanced sorting and Comparator idioms
 * - functional/    Stream API examples: mapping, grouping, collectors, parallel streams
 * - bestpractices/ Small examples demonstrating defensive copying and pitfalls
 * - Person.java    Immutable model used across examples
 * - Book.java      Immutable model used for sorting/grouping examples
 * - Demo.java      Compact runner that invokes representative examples
 *
 * Prerequisites
 * -------------
 * - Java 21 (module and examples use records and modern JDK APIs)
 * - Lombok enabled (annotation processing active in the IDE or via Maven) for
 *   convenience methods such as generated "withX(...)" methods on records.
 *
 * Build & run (module-specific)
 * -----------------------------
 * The project uses Maven. From the repository root you can run:
 *   ./mvnw -pl tp3 -am test
 * to execute the examples' tests (if any). To run the Demo main:
 *   ./mvnw -pl tp3 -am exec:java -Dexec.mainClass=fr.univtln.bruno.samples.java101.tp3.Demo
 *
 * Notes on site generation
 * -------------------------
 * Each module is expected to publish a Maven site. To generate the module site run from the reactor root:
 *   ./mvnw site
 * The module's generated site will be available under tp3/target/site when the parent site lifecycle is executed.
 *
 * Pedagogical notes (moved from README)
 * -------------------------------------
 * 1) Anonymous comparator implementations vs lambdas / method references
 *    - Some examples deliberately use anonymous Comparator implementations. This is intentional to expose the
 *      full shape of the compare method for students new to the concept.
 *    - Each such example contains a commented, idiomatic alternative using lambdas or Comparator factory methods
 *      (e.g. Comparator.comparing, Comparator.comparingInt). In class, present the anonymous form first then the
 *      concise modern form.
 *
 * 2) Lombok `@With` and Java records
 *    - Model classes such as Person and Book are Java records (Java 21). Lombok's `@With` is used on record
 *      components to generate immutable "withX(...)" methods that simplify certain exercises.
 *    - Records already generate equals/hashCode/toString; Lombok is only used for convenience helpers. Ensure
 *      annotation processing is active in the IDE so generated methods are visible to students.
 *    - When showing code to beginners, you may optionally present manual "with" factory methods to explain
 *      what Lombok generates.
 *
 * 3) Edge cases to illustrate during class
 *    - Null handling: prefer factories that validate parameters. Show Comparator.nullsFirst / nullsLast usage.
 *    - equals vs compareTo consistency: demonstrate that compareTo(a,b)==0 should imply a.equals(b) to avoid
 *      surprising behavior in sorted collections (TreeSet/TreeMap). Provide tests that assert this property.
 *    - Concurrent modification: demonstrate ConcurrentModificationException when mutating collections during
 *      iteration and discuss iterator.remove, CopyOnWriteArrayList and concurrent collections as solutions.
 *
 * Quick teaching pointers
 * -----------------------
 * - Emphasize complexity characteristics for List, Set and Map implementations.
 * - Show concretes: ArrayDeque instead of Stack, List.of/Set.of/Map.of for immutable samples.
 * - Prefer Comparator.comparing + thenComparing in examples after the students understand anonymous comparator shape.
 *
 * This package contains subpackages for list/map/set/queue/comparable/functional/bestpractices examples used in the module.
 */
package fr.univtln.bruno.samples.java101.tp3;
