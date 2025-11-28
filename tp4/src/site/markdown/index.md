# TP4 — Functional Programming & Modern Java

This module illustrates functional programming best practices and modern Java features (up to Java 25).

## What you will learn

- Lambdas and method references
- Standard functional interfaces (Predicate, Function, Supplier, Consumer, Operators)
- Stream API: creation, intermediate and terminal operations, collectors
- Optionals, records, and immutable data patterns
- Parallel streams and avoiding shared mutable state
- When to prefer functional style vs imperative code

## Quick map of examples

- `lambda` — lambda basics, method references, constructor references
- `functionalinterfaces` — standard and custom functional interfaces examples
- `streams` — creation, intermediate ops, collectors, parallel streams
- `misc` — Optional examples and other utilities

## How to run

```bash
# From project root
./mvnw -pl tp4 -am clean package
java -cp tp4/target/tp4-0.0.1-SNAPSHOT.jar fr.univtln.bruno.samples.java101.tp4.Demo
```

## Learning tips for students

1. Read the lecture notes before running examples:
   - https://bruno.univ-tln.fr/notebooks/notebook-java-after8/01_java_after8_L_Functionnal.html
   - https://bruno.univ-tln.fr/notebooks/notebook-java-after8/02_java_after8_L_Stream.html
   - https://bruno.univ-tln.fr/notebooks/notebook-java-after8/03_java_after8_L_Misc.html

2. Focus on small functions and pure transformations.
3. Avoid shared mutable state in streams (especially parallel streams).
4. Prefer `Collectors` and immutable collections when possible.


---

[← Back to Parent](../index.html)

