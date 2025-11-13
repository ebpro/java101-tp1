# Java 101 Lab — Examples and Teaching Path

This repository contains small Java examples designed to illustrate core concepts from beginner to intermediate/advanced: simple POJOs, immutability, the Builder pattern, factories, Lombok, unit tests and packaging.

Purpose of this README: present the topics in a learning-friendly order (from easiest to hardest), explain how to build and run the project, and propose progressive exercises for students.

---

## Learning path (easy → advanced)

1. Mutable POJO with validation (`Person`)
   - Goal: show conventional getters/setters, constructor validation, and helper methods (`fullName`, `toString`).
   - Key points: mutable objects are convenient but require careful reasoning about state and thread-safety (the `instanceCount` in `Person` is not atomic).

2. Factory methods (`fr.univtln.bruno.samples.java101.tp1.factory.PersonFactory`)
   - Goal: present named factory methods (`of`, `defaultPerson`, `teenager`) to centralize validation and construction logic.

3. Immutable value via constructors (`Address`)
   - Goal: show a simple immutable type (final fields) and overloaded constructors.
   - Key points: input normalization (null → `""`), a readable `toString()`.

4. Builder pattern (`fr.univtln.bruno.samples.java101.tp1.builder.AddressWithBuilder`)
   - Goal: build immutable objects with many optional fields using a fluent builder.
   - Key points: builder is mutable, validation happens at `build()` time.

5. Immutable value + Builder (`fr.univtln.bruno.samples.java101.tp1.immutable.PersonImmutable`)
   - Goal: build thread-safe value objects via a private constructor and a nested Builder.
   - Key points: builder normalizes/validates inputs before calling the private constructor.

6. Lombok examples (`fr.univtln.bruno.samples.java101.tp1.lombok`)
   - Goal: compare handwritten boilerplate with Lombok-generated code (`@Builder`, `@Getter`, `@AllArgsConstructor`, etc.).
   - Key points: Lombok generates code at compile time — configure your IDE with the Lombok plugin to avoid editor warnings.

7. Tests and logging (JUnit, AssertJ, Logback)
   - Goal: demonstrate unit testing and readable assertions (AssertJ) and structured logging (SLF4J + Logback).
   - Key points: tests live in `src/test/java`; assertions should focus on behavior (happy path + edge cases).

8. Packaging: creating a fat/uber JAR with Maven Shade (profile `shadedjar`)
   - Goal: show how to create an executable JAR that embeds dependencies for easy distribution.
   - Key points: `maven-shade-plugin` supports transformers, filters and optional minimization; be careful with `minimizeJar` (it can break reflection) and with JPMS (`module-info.class`).

---

## About Maven

Maven is the build tool used for this project. It manages compilation, testing, packaging and dependencies.

- Recommended version: any recent Maven 3.x (the wrapper included in this repo will download a compatible Maven automatically).
- Java: make sure your `JAVA_HOME` points to the JDK used for the project (this repo targets JDK 21 via the compiler plugin). You can check with `java -version` and `echo $JAVA_HOME` (or `set JAVA_HOME` on Windows).
- `settings.xml`: advanced users can customize `~/.m2/settings.xml` to configure mirrors, credentials, or local repository location.
- Profiles: this project defines a `shadedjar` profile used to produce an uber-jar. Activate it with `-Pshadedjar`.
- Common CLI options students will use:
  - `-DskipTests=true` or `-DskipTests` — skip tests during packaging (useful for faster iteration)
  - `-T 1C` — enable multi-threaded builds (use carefully on CI)
  - `-U` — force update of snapshots

Troubleshooting tips:
- If dependency downloads fail, check your network or company proxy settings and your `~/.m2/settings.xml`.
- If classfile version errors occur, ensure `maven.compiler.release` matches your JDK and that `JAVA_HOME` is correct.
- To clean build state: `./mvnw clean` then `./mvnw package`.

---

## Maven Wrapper

This project includes the Maven Wrapper files (`mvnw`, `mvnw.cmd` and the `.mvn/wrapper` directory). Use the wrapper so students do not need a local Maven installation and the build is reproducible across machines and CI.

- Unix / macOS:

```bash
./mvnw <goal>   # e.g. ./mvnw verify
```

- Windows (CMD/PowerShell):

```
mvnw.cmd <goal>
```

Notes:
- Ensure the `mvnw` script is executable on Unix-like systems: `chmod +x mvnw` (one-time).
- The wrapper will download a Maven distribution the first time it runs; it does not replace the need for a compatible JDK (set `JAVA_HOME` if necessary).
- If the wrapper is missing you can generate it with: `mvn -N io.takari:maven:wrapper` (requires a local Maven for generation).

---

## Useful commands

- Run full build and tests:

```bash
./mvnw verify
```

- Build main artifact and attached artifacts (sources & javadoc):

```bash
./mvnw clean package
```

- Build the attached shaded (uber) JAR (skip tests for speed):

```bash
./mvnw -Pshadedjar -DskipTests clean package
```

The shaded JAR (attached) will be created at:

```
target/tp1-0.0.1-SNAPSHOT-withdependencies.jar
```

Run the shaded jar:

```bash
java -jar target/tp1-0.0.1-SNAPSHOT-withdependencies.jar
```

- JaCoCo coverage report (after `verify`):

```
target/site/jacoco/index.html
```

- Javadoc & javadoc jar:

```
target/apidocs/
target/tp1-0.0.1-SNAPSHOT-javadoc.jar
```

- Sources jar:

```
target/tp1-0.0.1-SNAPSHOT-sources.jar
```
