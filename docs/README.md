# TP2 UML Class Diagram

This directory contains the PlantUML source for the TP2 class diagram.

## File
- `tp2-class-diagram.puml` - PlantUML source showing the complete Vehicle domain hierarchy

## How to view the diagram

### Option 1: Online PlantUML viewer
1. Open https://www.plantuml.com/plantuml/uml/
2. Copy the content of `tp2-class-diagram.puml`
3. Paste into the web editor
4. The diagram will render automatically

### Option 2: VS Code with PlantUML extension
1. Install the "PlantUML" extension in VS Code
2. Open `tp2-class-diagram.puml`
3. Press `Alt+D` (or `Option+D` on Mac) to preview

### Option 3: Command-line with PlantUML
```bash
# Install PlantUML (macOS with Homebrew)
brew install plantuml

# Generate PNG
plantuml tp2-class-diagram.puml

# Generate SVG (scalable)
plantuml -tsvg tp2-class-diagram.puml
```

### Option 4: IntelliJ IDEA
1. Install the "PlantUML Integration" plugin
2. Open `tp2-class-diagram.puml`
3. The preview pane will show the diagram automatically

## What the diagram shows

The diagram illustrates:
- **Abstract base classes**: `Vehicle` and `MotorVehicle` (intermediate abstraction)
- **Interface Segregation Principle**: `Refuelable` (fuel-based) vs `Rechargeable` (battery-based)
- **Concrete implementations**:
  - `Car` (fuel-based, implements `Refuelable`)
  - `ElectricCar` (battery-based, implements `Rechargeable`, does NOT implement `Refuelable`)
  - `Bike` (manual, lightweight)
  - `ElectricBike` (battery-assisted, implements `Rechargeable`)
  - `ServiceCar` (resolves default method conflicts)
- **Delegation**: `Driver` delegates to `Drivable`
- **Composition**: `Manufacturer` creates `Vehicle` instances

## Key teaching points visible in diagram

1. **Proper hierarchy**: `ElectricCar` extends `MotorVehicle` but does NOT inherit `Refuelable`
2. **ISP**: Small, focused interfaces (`Refuelable` vs `Rechargeable`)
3. **Multiple inheritance via interfaces**: `ElectricCar` implements `Drivable`, `Electric`, and `Rechargeable`
4. **Composition over inheritance**: `Driver` uses composition (has-a) rather than inheritance (is-a)

