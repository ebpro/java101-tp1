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

   - New: `PersonValueObject` (record)
     - File: `src/main/java/fr/univtln/bruno/samples/java101/tp1/immutable/PersonValueObject.java`
     - Concepts for students:
       - Java `record` as a lightweight immutable data carrier.
       - Compact canonical constructor to validate invariants (null checks, non-negative age).
       - Static factory `of(...)` as a convenient named constructor.
       - Non-mutating update helpers (`withName`, `withEmail`, `withAge`, `incrementAge`) that return new instances.
       - `merge(...)` pattern: merging non-null fields from another instance to produce a combined result.
       - Why value objects make reasoning, testing and concurrency simpler than mutable POJOs.

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

2. **Install Git hooks** (recommended for quality checks):

```bash
./install-hooks.sh
```

This installs hooks that:
- ✅ Compile code and run tests before each commit
- 📝 Validate commit message format (Conventional Commits)
- 🚀 Run full verification before pushing

See `.githooks/README.md` for details.

3. Use the Maven wrapper included in the project to run builds reproducibly:

```bash
# Unix / macOS
./mvnw clean package

# Run the demo
java -cp target/tp1-0.0.1-SNAPSHOT.jar fr.univtln.bruno.samples.java101.tp1.Demo
```

4. Produce a site (reports + javadoc):

```bash
./mvnw -DskipTests site
# Open site report locally
open target/site/index.html
```

5. Build a shaded (fat) JAR for distribution:

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

## Note about class names and conventions

In this teaching repository some class names intentionally include the pattern
or style in their name (for example: `PersonWithFactory`, `PersonImmutable`,
`PersonValueObject`, `PersonWithBuilder`, `PersonSingleton`). This is done to
make the educational intent explicit and to help beginners quickly see which
pattern is illustrated by each class.

Important: these pedagogical names are NOT recommended for production code.
In real projects prefer clean, domain-driven names and rely on packages or
suffixes to indicate roles or patterns. Suggested mappings and guidelines:

- Production naming examples:
  - Entity: `Person` or `PersonEntity`
  - DTO (transfer object): `PersonDto` or `PersonDTO`
  - Factory: `PersonFactory` (not `PersonWithFactory`)
  - Builder: `PersonBuilder` or `AddressBuilder`
  - Immutable/value object: `PersonValue` or use a `record` named `Person` in an `immutable` package
  - Singleton/test utility: `PersonSingleton` only when explicitly demonstrating the pattern; prefer dependency injection or `enum` singletons in real systems

- Guidelines:
  - Use nouns for domain objects (Person, Address, Order).
  - Use verbs or -er suffix for services that perform actions (PersonService,
    OrderProcessor).
  - Use packages to group patterns: `..factory`, `..builder`, `..immutable`, `..lombok`.
  - Avoid the `With` infix in production names (it is only used here for clarity).

If you adapt these examples into your own project, apply production naming
conventions and regroup pattern examples into dedicated packages so code
remains clear and idiomatic.

---

## Git Hooks for Code Quality

This project includes Git hooks to help maintain code quality and consistent
practices:

### Available Hooks

- **pre-commit**: Compiles code and runs tests before each commit
- **commit-msg**: Validates commit messages follow Conventional Commits format
- **pre-push**: Runs full verification (`mvn clean verify`) before pushing

### Installation

```bash
./install-hooks.sh
```

### Benefits for Students

- Learn professional development practices early
- Avoid committing broken code
- Practice meaningful commit messages
- Build confidence through automated checks

### Skipping Hooks (when needed)

```bash
git commit --no-verify -m "message"   # Skip pre-commit
git push --no-verify                   # Skip pre-push
```

For complete documentation, see `.githooks/README.md`.

---

## For Instructors

This teaching repository demonstrates multiple Java patterns in a compact way.
Students are encouraged to:
- Read the code and understand why each pattern is used
- Run tests to see the behaviour
- Modify examples and observe the effects
- Compare mutable vs immutable approaches
- Learn when factories, builders or records are appropriate

The Maven site generation (with Javadoc and test reports) helps students
see the documentation and coverage in a professional format.
