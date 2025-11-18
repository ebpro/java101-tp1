# Java 101 — Teaching Project

A comprehensive multi-module Maven project demonstrating Java fundamentals, design patterns, and advanced object-oriented programming concepts.

## 📚 Modules

This project is organized into independent modules for progressive learning:

### [TP1 — Fundamentals & Patterns](./tp1/index.html)
Learn core Java patterns and best practices:
- POJOs and mutability
- Immutability and value objects
- Factory and builder patterns
- Lombok for boilerplate reduction

### [TP2 — Interfaces & Polymorphism](./tp2/index.html)
Master advanced OOP concepts:
- Interfaces and contracts
- Polymorphism and inheritance
- Composition and delegation
- Interface segregation principle

## 📊 Reports & Documentation

### Consolidated Reports
**[View Aggregated Reports](./report-aggregate/index.html)** — All quality metrics in one place
- JaCoCo coverage across all modules
- Combined quality analysis

### Module Reports
- **[TP1 Reports](./tp1/project-reports.html)** — Tests, coverage, quality metrics
- **[TP2 Reports](./tp2/project-reports.html)** — Tests, coverage, quality metrics

### API Documentation
- [TP1 Javadoc](./tp1/apidocs/index.html)
- [TP2 Javadoc](./tp2/apidocs/index.html)

## 🚀 Quick Start

```bash
# Clone and build
git clone https://github.com/ebpro/java101-tp1.git
cd java101-tp1

# Install quality hooks (recommended)
./install-hooks.sh

# Build all modules
./mvnw clean package

# Run tests
./mvnw test

# Generate this site
./mvnw clean verify site
./build-site.sh --with-tests
```

## 🎓 For Students

### Recommended Learning Path:
1. Start with **[TP1](./tp1/index.html)** to learn fundamental patterns
2. Progress to **[TP2](./tp2/index.html)** for advanced OOP concepts
3. Explore the **tests** to see patterns in action
4. Review **[quality reports](./report-aggregate/index.html)** to understand code metrics

### Key Concepts Covered:
- **Design Patterns**: Factory, Builder, Singleton, Value Object
- **OOP Principles**: Encapsulation, Inheritance, Polymorphism, Composition
- **Best Practices**: Immutability, Interface Segregation, Testing
- **Modern Java**: Records, Default Methods, Lombok

## 🛠️ Technology Stack

- **Java 21** — Modern Java LTS version
- **Maven 3.9+** — Build and dependency management
- **JUnit 5** — Unit testing framework
- **AssertJ** — Fluent assertions
- **Lombok** — Boilerplate reduction
- **JaCoCo** — Code coverage analysis
- **Checkstyle, PMD, SpotBugs** — Code quality tools

## 📖 Documentation

Each module contains comprehensive documentation:
- **Learning guides** with concept explanations
- **Code examples** demonstrating patterns
- **Unit tests** showing usage
- **Javadoc** for API reference
- **Quality reports** for code metrics

## ℹ️ Project Information

- **[Project Modules](./modules.html)** — Module hierarchy
- **[Licenses](./licenses.html)** — Dependencies and licenses
- **[SCM Info](./scm.html)** — Source code management
- **[Team](./team.html)** — Project contributors

---

**Architecture:** Multi-module Maven project with a dedicated aggregation module for consolidated reports.
**Build:** Automated CI/CD with GitHub Actions deploying to GitHub Pages.
**Version:** 0.0.1-SNAPSHOT


