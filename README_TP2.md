# TP2 — Inheritance, Abstraction, Interfaces and Delegation (Vehicle example)

> Compact, post-lecture teaching example that illustrates inheritance, abstract
> classes, interfaces (with default methods), delegation (composition) and a
> few Java-specific features (covariant returns, static hiding, final methods).

Table of contents
- [Why this example](#why-this-example)
- [Quick start](#quick-start)
- [Learning outcomes](#learning-outcomes)
- [Pedagogical sequence (recommended reading order)](#pedagogical-sequence-recommended-reading-order)
- [Files & learning map](#files--learning-map)
- [Key tests (to inspect)](#key-tests-to-inspect)
- [Hands-on exercises (ready-to-run)](#hands-on-exercises-ready-to-run)
- [Teaching tips & classroom flow](#teaching-tips--classroom-flow)
- [Next steps & optional refactors](#next-steps--optional-refactors)

---

## Why this example

The Vehicle domain is intentionally familiar and concrete. It helps students
focus on the object-oriented concepts (is‑a vs has‑a, contracts, default
methods) without being distracted by domain complexity. Implementations are
small and deterministic so tests are short and readable.

## Quick start

Run the demo (prints short illustrative outputs):

```bash
# from repository root
mvn -q exec:java
# or explicit main class
mvn -q -Dexec.mainClass=fr.univtln.bruno.samples.java101.tp2.app.DemoVehicle exec:java
```

Run the unit tests (includes TP2 tests):

```bash
mvn test
```

Open the project in an IDE and inspect the small classes under
`src/main/java/.../tp2` and the tests under `src/test/java/.../tp2`.

---

## Learning outcomes
After studying the code and running the demo/tests students will be able to:
- Explain the purpose of an abstract base class and how it can provide a
  partial implementation (template method pattern).
- Implement concrete subclasses that extend state/behaviour and remain
  substitutable via polymorphism.
- Design and use interfaces (contracts) including default methods and resolve
  conflicts when multiple defaults are present.
- Prefer composition/delegation over inheritance when appropriate.
- Recognize Java-specific features: covariant returns, static method hiding,
  and final methods.

---

## Pedagogical sequence (recommended reading & coding order)
Follow this order in class or guided exercises — each step builds on the
previous:

1. `Vehicle` (abstract): identity, `move()` (abstract) and `description()`
   (template method that calls `move()`). Explain `protected` vs `private`.
2. `Car` implementation: add simple state (`fuelLevel`) and behaviour
   (`move()`, `refuel()`, `start()`). Run `VehicleTest` showing `move()`.
3. `ElectricCar`: show a different resource (`batteryLevel`) and `charge()`.
4. `Covariant copy()`: show how `Car.copy()` returns `Car`; run
   `CovariantReturnTest`.
5. `Drivable` and `Maintainable` interfaces: default methods and conflict.
6. `ServiceCar`: explicit override resolving default-method conflict;
   discuss why Java forces this.
7. **`Refuelable` and `Rechargeable` interfaces**: demonstrate Interface
   Segregation Principle (ISP) by extracting resource management into small,
   focused contracts. Run `InterfaceSegregationTest`.
8. `Driver` (delegation) and `Manufacturer` (composition/factory): when to use
   has‑a vs is‑a.
9. Static hiding & final methods: `category()` and `getId()` behaviours.

---

## Files & learning map (quick reference)
| File | Concept illustrated |
|---|---|
| `model/Vehicle.java` | Abstract base, partial impl (`description()`), covariant `copy()` |
| `api/Drivable.java` | Interface with default method `start()` and `drive()` contract |
| `api/Electric.java` | Electric capability (`charge()`) |
| `api/Maintainable.java` | Default method conflict (another `start()`) |
| `api/Refuelable.java` | **ISP: small interface for fuel management** |
| `api/Rechargeable.java` | **ISP: small interface for battery management** |
| `impl/Car.java` | Concrete class, `fuelLevel`, `start()`/`move()`/`refuel()`, **implements Refuelable** |
| `impl/ElectricCar.java` | Electric car: `batteryLevel`, `charge()`, **implements Rechargeable** |
| `impl/Bike.java` | Lightweight vehicle: contrast with fuel/electric models |
| `impl/ServiceCar.java` | Resolves default-method conflict by composing behaviours |
| `service/Driver.java` | Delegation (Driver -> Drivable) |
| `service/Manufacturer.java` | Simple factory/composition example |
| `app/DemoVehicle.java` | Runnable demo that prints the examples |

> Note: the table shows the main pedagogical intent — implementations are
> intentionally minimal so students can read the whole example in a few
> minutes.

---

## Key tests (read to understand expected behaviour)
Open these tests in the IDE — they are small and show the intended outcomes:
- `VehicleTest` — basic behaviour across implementations
- `PolymorphismTest` — upcasting/downcasting and runtime dispatch
- `CovariantReturnTest` — copy returns concrete types
- `StaticHidingTest` — static method hiding checks
- `DefaultMethodConflictTest` — `ServiceCar` resolves default method
- `DelegationTest` — `Driver` delegates correctly
- `DescriptionTest` — `description()` delegates to subclass `move()`
- `CompositionTest` — `Manufacturer` produces a `Car`

---

## Hands-on exercises (ready-to-run, with hints)
These are designed to be short (5–15 minutes) and used in class or homework.

Exercise 1 — Visibility (5–10 min)
- Task: change `Vehicle.id` to `private` and add a `protected String idForSubclass()`
  or a `public getId()` accessor if missing. Re-run tests and discuss why
  `protected` can be dangerous in large codebases.
- Hint: only modify `Vehicle` — no changes needed in tests.

Exercise 2 — Interface segregation (10–15 min)
- Task: create `Refuelable` with `refuel(double)` and have `Car` implement it.
  Update the code and add a small test that calls `refuel()` and checks fuel.
- Hint: this shows the Interface Segregation Principle: keep interfaces small.

Exercise 3 — Replace status strings by exceptions (15–25 min)
- Task: change methods that currently return error strings (e.g.
  "cannot start (no fuel)") to throw a checked `CannotStartException`.
  Discuss design tradeoffs and update tests accordingly.
- Hint: ask students: which API is easier to use and which is more robust?

Exercise 4 — Add logging (10–20 min)
- Task: add `slf4j` + `logback` and log `start()`/`move()` events.
  Run the demo to see logs; discuss why logging is helpful in real projects.

---

## Teaching tips & classroom flow
- 10 minute live coding: implement `Vehicle` and `Car` and run `description()`
  to show dynamic dispatch.
- 10–15 minute group work: add `ElectricCar` and `charge()`; compare behaviours.
- 10 minute demo: create a default-method conflict with `Maintainable` and let
  students fix it (force them to override).
- Finish with a short discussion on composition vs inheritance with `Driver`.

---

## Next steps / optional refactors
- Add `Refuelable` and `Rechargeable` interfaces and move `refuel()`/`charge()`
  methods there (interface segregation).
- Add small property-based tests (Jqwik) to check invariants (fuel/battery
  never negative).
- Generate the Javadoc site and include a short TP2 page on the project site.

---

If you want I can implement any of the small exercises as pull-requests
(with tests), or generate a short slide deck summarising the TP2 concepts.

---

*End of improved TP2 README.*
