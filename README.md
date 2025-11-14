# Java 101 — TP1 (Teaching Examples)

This repository contains compact Java examples used for teaching Java fundamentals.
The code is arranged to guide students from simple concepts to slightly more advanced
patterns. The aim is practical learning: run the code, read the short examples,
and modify them to explore behaviour.

This README is written for students and instructors. It is intentionally concise
and ordered from easiest to more advanced topics.

---

## Learning path (easy → harder)

1. Mutable objects (POJO)
   - File: `src/main/java/.../Person.java`
   - Concepts: fields, getters/setters, simple validation, mutability trade-offs.

2. Simple factories and named constructors
   - File: `src/main/java/.../factory/PersonWithFactory.java`
   - Concepts: static factory methods (`of`, `defaultPerson`, `teenager`) that
     improve readability and centralise construction logic.

3. Immutable value objects
   - File: `src/main/java/.../Address.java` and `immutable/PersonImmutable.java`
   - Concepts: final fields, private constructors, thread-safety and why immutability helps.

4. Builder pattern
   - File: `src/main/java/.../builder/AddressWithBuilder.java`
   - Concepts: fluent API for building complex/optional configurations, `build()` validation.

5. Lombok-powered examples
   - Package: `src/main/java/.../lombok`
   - Concepts: reduce boilerplate with annotations (`@Getter`, `@Builder`, `@ToString`).
     Note: enable annotation processing in your IDE to avoid warnings.

6. Packaging, testing and site generation
   - Packaging: Maven Shade profile `shadedjar` for a fat JAR.
   - Testing: JUnit + AssertJ in `src/test/java`.
   - Site: `mvnw site` generates project reports and javadoc under `target/site`.

---

## Quick start (students)

1. Ensure you have a JDK (21 is the target). Set `JAVA_HOME` accordingly.

2. Use the Maven wrapper included in the project to run builds reproducibly:

```bash
# Unix / macOS
./mvnw clean package

# Run the demo
java -cp target/tp1-0.0.1-SNAPSHOT.jar fr.univtln.bruno.samples.java101.tp1.Demo
```

3. Produce a site (reports + javadoc):

```bash
./mvnw -DskipTests site
# Open site report locally
open target/site/index.html
```

4. Build a shaded (fat) JAR for distribution:

```bash
./mvnw -Pshadedjar -DskipTests clean package
java -jar target/tp1-0.0.1-SNAPSHOT.jar
```

---

## Notes about Maven and the wrapper

- This repo includes the Maven Wrapper (`mvnw`) to ensure the same Maven
  version is used by all students. The wrapper downloads the configured
  Maven distribution automatically.
- If you have Maven locally and prefer to use it, ensure it is recent (3.x).
- If you see classfile or compilation errors, verify your `JAVA_HOME` and the
  `maven.compiler.release` configuration in `pom.xml`.

---

## Continuous Integration & GitHub

This project includes GitHub Actions workflows that:
- Build and test the project on multiple Java versions (CI workflow).
- Run CodeQL security analysis (CodeQL workflow).
- Build and deploy the Maven site to GitHub Pages (deploy-pages workflow).

Notes for instructors:
- CI runs on GitHub-hosted runners and uses the Maven wrapper; no additional
  setup is required in most cases.
- The GitHub Pages workflow publishes the generated site artifacts; see the
  workflow YAML in `.github/workflows/deploy-pages.yml`.

---

## What I changed in this repository (instructor-facing summary)

- Added clear English documentation in package-level Javadoc.
- Updated `Demo` to show factory, builder and singleton usage.
- Rewrote this README in English, ordered by difficulty and focused on
  students (removed exercise sections per request).
- Ensured Javadoc warnings are addressed where practical.

---

If you want, I can also:
- Add a short unit test for `PersonSingleton` demonstrating initialization.
- Add a small section with common IDE setup tips (IntelliJ, VS Code) for Lombok.
- Set up GitHub Pages publishing to reuse an existing Java 21 build artifact in CI.
