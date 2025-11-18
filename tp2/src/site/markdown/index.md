# TP2 — Interfaces, Polymorphism & Advanced OOP

This module demonstrates advanced object-oriented programming concepts in Java,
focusing on interfaces, polymorphism, composition, and delegation patterns.

---

## 📚 Learning Path

### 1. Interfaces & Contracts

**Package:** `fr.univtln.bruno.samples.java101.tp2.api`

**Key Interfaces:**
- `Drivable` — Basic vehicle operations with default method
- `Maintainable` — Maintenance operations
- `Electric` — Electric vehicle capabilities
- `Rechargeable` — Battery charging interface
- `Refuelable` — Fuel tank interface

**Concepts:**
- Interface as contract (behavior specification)
- Default methods (Java 8+)
- Multiple interface implementation
- Interface segregation principle
- Marker interfaces

**Why interfaces?**
- Define behavior without implementation
- Multiple inheritance of behavior
- Loose coupling between components
- Easy to mock for testing
- Plugin architecture support

---

### 2. Abstract Classes & Hierarchy

**Package:** `fr.univtln.bruno.samples.java101.tp2.model`

**Key Classes:**
- `Vehicle` — Base abstract class
- `MotorVehicle` — Specialized abstract class for motor vehicles

**Concepts:**
- Abstract classes for common behavior
- Template method pattern
- Protected constructors
- Shared state across hierarchy
- When to use abstract class vs interface

**Abstract vs Interface:**
- Abstract: Partial implementation, shared state
- Interface: Pure contract, no state

---

### 3. Concrete Implementations

**Package:** `fr.univtln.bruno.samples.java101.tp2.impl`

**Vehicle Types:**
- `Bike` — Simple non-motor vehicle
- `ElectricBike` — Electric-powered bike
- `Car` — Gasoline-powered car
- `ElectricCar` — Electric-powered car
- `ServiceCar` — Special maintenance vehicle

**Concepts:**
- Implementing multiple interfaces
- Overriding abstract methods
- Specialized behavior per type
- State management (fuel, battery)
- Covariant return types in `copy()` methods

---

### 4. Polymorphism in Action

**Test:** `PolymorphismTest.java`

**Concepts Demonstrated:**

#### Static vs Dynamic Binding
```java
Vehicle v = new Car("C1");
v.category(); // Dynamic dispatch
```

#### Interface Polymorphism
```java
Drivable vehicle = new Car("C1");
vehicle.drive(); // Interface method
```

#### Covariant Return Types
```java
Car original = new Car("C1");
Car copy = original.copy(); // Returns Car, not Vehicle
```

**Benefits:**
- Write code against abstractions
- Swap implementations easily
- Extensible design
- Testability through mocking

---

### 5. Composition & Delegation

**Classes:**
- `Driver` — Composes with `Drivable`
- `Manufacturer` — Factory for vehicles

**Concepts:**

#### Composition over Inheritance
- `Driver` has-a `Drivable` (not is-a)
- Flexible runtime behavior changes
- No tight coupling to hierarchy
- Easier testing and mocking

#### Delegation Pattern
```java
class Driver {
    private Drivable vehicle;

    String drive() {
        return vehicle.drive(); // Delegates to vehicle
    }
}
```

**Why composition?**
- More flexible than inheritance
- Avoid fragile base class problem
- Support multiple behaviors
- Runtime behavior changes

---

### 6. Default Methods & Conflicts

**Test:** `DefaultMethodConflictTest.java`

**Concepts:**
- Default method implementation in interfaces
- Conflict resolution when implementing multiple interfaces
- Explicit disambiguation with `Interface.super.method()`

**Example:**
```java
interface A { default String m() { return "A"; } }
interface B { default String m() { return "B"; } }

class C implements A, B {
    @Override
    public String m() {
        return A.super.m(); // Explicit choice
    }
}
```

---

### 7. Interface Segregation

**Test:** `InterfaceSegregationTest.java`

**Principle:** Clients shouldn't depend on interfaces they don't use.

**Good Design:**
```java
// Segregated interfaces
interface Drivable { void drive(); }
interface Refuelable { void refuel(); }

// Classes implement only what they need
class ElectricCar implements Drivable, Rechargeable { }
class GasCar implements Drivable, Refuelable { }
```

**Bad Design:**
```java
// Fat interface
interface Vehicle {
    void drive();
    void refuel();   // Not all vehicles refuel!
    void charge();   // Not all vehicles charge!
}
```

---

### 8. Static Hiding (Not Polymorphism!)

**Test:** `StaticHidingTest.java`

**Important:** Static methods are NOT polymorphic!

```java
class Vehicle {
    static String category() { return "Vehicle"; }
}

class Car extends Vehicle {
    static String category() { return "Car"; }  // HIDING, not overriding
}

Vehicle v = new Car();
v.category(); // Calls Vehicle.category(), not Car.category()!
```

**Key Lesson:** Use instance methods for polymorphism.

---

## 🧪 Testing & Coverage

**Test Classes:**
- `VehicleTest` — Basic vehicle operations
- `PolymorphismTest` — Dynamic dispatch examples
- `CompositionTest` — Composition pattern
- `DelegationTest` — Delegation pattern
- `InterfaceSegregationTest` — ISP principle
- `DescriptionTest` — Default method usage
- `CovariantReturnTest` — Return type covariance
- `DefaultMethodConflictTest` — Multiple interface conflicts
- `StaticHidingTest` — Static method hiding
- `ResourceStateTest` — State management (fuel, battery)

**Run tests:**
```bash
../../mvnw test
```

---

## 📊 Reports & Documentation

- **[Javadoc](./apidocs/index.html)** — Full API documentation
- **[Test Report](./surefire-report.html)** — All test results
- **[JaCoCo Coverage](./jacoco/index.html)** — Code coverage metrics
- **[Checkstyle](./checkstyle.html)** — Style compliance
- **[PMD](./pmd.html)** — Code quality analysis
- **[SpotBugs](./spotbugs.html)** — Potential bugs

---

## 💡 Key Takeaways

### Design Principles

1. **Program to interfaces, not implementations**
   - Depend on abstractions
   - Swap implementations easily

2. **Favor composition over inheritance**
   - More flexible
   - Avoid coupling

3. **Interface Segregation Principle**
   - Many small interfaces > one large interface
   - Clients use only what they need

4. **Liskov Substitution Principle**
   - Subtypes must be substitutable for base types
   - Maintain behavioral contracts

### When to Use What

| Pattern | Use When |
|---------|----------|
| Interface | Define pure contract, multiple implementations |
| Abstract Class | Share implementation + state across hierarchy |
| Composition | Need flexible behavior, avoid inheritance |
| Default Methods | Add methods to existing interfaces without breaking clients |

---

## 🎓 For Students

### Recommended Study Order:

1. **Start with interfaces** (`api` package)
   - Understand contracts
   - See default methods

2. **Explore abstract classes** (`model` package)
   - Compare with interfaces
   - Understand template pattern

3. **Study implementations** (`impl` package)
   - See polymorphism in action
   - Different vehicle types

4. **Run tests** (`test` package)
   - See patterns in use
   - Understand edge cases

5. **Experiment**
   - Add new vehicle types
   - Create new interfaces
   - Try different compositions

### Exercises:

1. **Add a `Motorcycle` class**
   - Extends `MotorVehicle`
   - Implements `Drivable` and `Refuelable`
   - Add unique features (e.g., `wheelie()`)

2. **Create a `Fleet` class**
   - Manages multiple vehicles
   - Uses composition
   - Methods: `addVehicle()`, `getTotalFuel()`, etc.

3. **Implement a `MaintenanceSchedule` interface**
   - Add to appropriate vehicle types
   - Track maintenance history

4. **Add a `HybridCar` class**
   - Implements both `Refuelable` and `Rechargeable`
   - Manages two power sources

5. **Experiment with default methods**
   - Add a new default method to `Drivable`
   - See how existing classes behave
   - Override in some implementations

---

## 🔗 Navigation

- [← TP1 — Fundamentals](../tp1/index.html)
- [← Back to Parent](../index.html)
- [Aggregated Reports →](../report-aggregate/index.html)
- [All Reports](./project-reports.html)

