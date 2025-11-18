# TP1 — Java Fundamentals & Design Patterns

This module contains compact Java examples demonstrating core concepts and patterns.
The code is arranged to guide students from simple concepts to more advanced patterns.

> 🧠 **Key concepts (TP1 — at a glance)**
>
> - Class vs Object: a class is a blueprint (type); an object is a concrete instance of that class at runtime.
> - Members: fields store state, methods define behavior; encapsulate state with private fields and public methods.
> - Constructor: special method to build valid objects and enforce invariants; can be overloaded; perform input validation.
> - Static vs Instance: `static` members belong to the class (shared by all instances), instance members belong to each object.
> - Final: `final` variable assigned once; `final` field makes a reference immutable; `final` method cannot be overridden; `final` class cannot be extended.
> - POJO: Plain Old Java Object — simple classes with fields and no framework dependencies; used to model data.
> - Immutability: objects that cannot change state after construction; safer and easier to reason about.
> - Factory: named creators (e.g., `of(...)`) centralizing construction and validation.
> - Builder: fluent, step-by-step object construction to avoid complex constructors.
> - Value object: defined by its data (equals/hashCode), typically immutable.
> - Lombok: annotations to remove boilerplate (getters, builder, equals/hashCode).

---

## 📚 Learning Path (Easy → Advanced)

### 1. Mutable Objects (POJO)

**Package:** `fr.univtln.bruno.samples.java101.tp1`

**Concepts:**
- Fields, getters, and setters
- Simple validation in constructors
- Understanding mutability trade-offs
- When mutable objects are appropriate

**Example:** Basic `Person` class with firstName, lastName, and age fields.

---

### 2. Factory Pattern & Named Constructors

**Package:** `fr.univtln.bruno.samples.java101.tp1.factory`

**Key Classes:**
- `PersonWithFactory` — Static factory methods pattern
- `PersonSingleton` — Singleton pattern demonstration

**Concepts:**
- Static factory methods: `of()`, `defaultPerson()`, `teenager()`
- Improved readability over constructors
- Centralized construction logic
- Encapsulation of complex initialization
- Singleton pattern for shared instances

**Why factories?**
- More expressive than constructors (named methods)
- Can return existing instances (caching)
- Can return subtypes
- Validation before construction

---

### 3. Immutability & Value Objects

**Package:** `fr.univtln.bruno.samples.java101.tp1.immutable`

**Key Classes:**
- `PersonValueObject` — Java `record` example
- `PersonImmutable` — Traditional immutable class with builder

**Concepts:**

#### Value Object Pattern (`PersonValueObject`)
- Java `record` as lightweight immutable data carrier
- Compact canonical constructor for validation
- Static factory `of(...)` as named constructor
- Non-mutating update methods: `withName()`, `withEmail()`, `withAge()`
- `incrementAge()` — functional update pattern
- `merge(...)` — combining instances without mutation

**Benefits of immutability:**
- Thread-safety by design
- Simpler reasoning about code
- Safe sharing across contexts
- Easier testing and debugging
- No defensive copying needed

**When to use:**
- Domain value objects (Money, Email, Address)
- DTOs and API responses
- Configuration objects
- Keys in collections

---

### 4. Builder Pattern

**Package:** `fr.univtln.bruno.samples.java101.tp1.builder`

**Key Classes:**
- `AddressWithBuilder` — Fluent API for complex object construction

**Concepts:**
- Fluent API design (method chaining)
- Building objects with many optional parameters
- Validation at build time
- Separation of construction from representation

**When to use builders:**
- Classes with many constructor parameters
- Many optional fields
- Need for validation before construction
- Want to ensure object consistency

**Example:**
```java
Address address = AddressWithBuilder.builder()
    .street("123 Main St")
    .city("Paris")
    .zipCode("75001")
    .build();
```

---

### 5. Lombok-Powered Examples

**Package:** `fr.univtln.bruno.samples.java101.tp1.lombok`

**Key Classes:**
- `PersonLombok` — Basic Lombok annotations
- `PersonLombokSecure` — Lombok with custom validation

**Concepts:**
- Reduce boilerplate with annotations
- `@Getter` / `@Setter` — automatic accessors
- `@Builder` — automatic builder pattern
- `@ToString`, `@EqualsAndHashCode` — automatic methods
- `@AllArgsConstructor`, `@NoArgsConstructor` — constructors
- Custom factory methods with Lombok builders

**Important:** Enable annotation processing in your IDE!

**When to use Lombok:**
- Reduce repetitive code
- Improve maintainability
- Focus on business logic
- Quick prototyping

**Caution:**
- Understand generated code
- Can complicate debugging
- Team must agree on usage

---

## 🧪 Testing

All patterns include comprehensive unit tests using:
- **JUnit 5** — Modern Java testing framework
- **AssertJ** — Fluent assertions

**Test packages mirror main packages:**
- `fr.univtln.bruno.samples.java101.tp1.*Test`

**Run tests:**
```bash
../../mvnw test
```

---

## 📦 Building & Packaging

### Standard JAR
```bash
../../mvnw clean package
java -cp target/tp1-0.0.1-SNAPSHOT.jar fr.univtln.bruno.samples.java101.tp1.Demo
```

### Fat JAR (Shaded)
```bash
../../mvnw clean package -Pshadedjar
java -jar target/tp1-0.0.1-SNAPSHOT-withdependencies.jar
```

---

## 📊 Reports & Documentation

- **[Javadoc](./apidocs/index.html)** — API documentation
- **[Test Report](./surefire-report.html)** — Unit test results
- **[JaCoCo Coverage](./jacoco/index.html)** — Code coverage
- **[Checkstyle](./checkstyle.html)** — Code style validation
- **[PMD](./pmd.html)** — Code quality analysis
- **[SpotBugs](./spotbugs.html)** — Bug detection

---

## 💡 Key Takeaways

1. **Mutability vs Immutability** — Understand trade-offs
2. **Factories** — Better than constructors for complex creation
3. **Value Objects** — Use records for immutable data
4. **Builders** — Essential for objects with many parameters
5. **Lombok** — Powerful but use wisely
6. **Testing** — Every pattern should be tested

---

## 🎓 For Students

### Recommended Study Order:
1. Read the source code (start with `Person.java`)
2. Run the tests and observe behavior
3. Modify examples and see the effects
4. Compare mutable vs immutable approaches
5. Try implementing your own examples

---

## 🔗 Navigation

- [← Back to Parent](../index.html)
- [TP2 — Interfaces & Polymorphism →](../tp2/index.html)
- [All Reports](./project-reports.html)
