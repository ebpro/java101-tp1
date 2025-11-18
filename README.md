# Java 101 — Teaching Examples

[![CI](https://github.com/ebpro/java101-tp1/actions/workflows/ci.yml/badge.svg)](https://github.com/ebpro/java101-tp1/actions/workflows/ci.yml)
[![CodeQL](https://github.com/ebpro/java101-tp1/actions/workflows/codeql.yml/badge.svg)](https://github.com/ebpro/java101-tp1/actions/workflows/codeql.yml)
[![Pages](https://github.com/ebpro/java101-tp1/actions/workflows/deploy-pages.yml/badge.svg)](https://github.com/ebpro/java101-tp1/actions/workflows/deploy-pages.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-21%2B-orange)](https://openjdk.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9%2B-blue)](https://maven.apache.org/)

A multi-module Maven project demonstrating Java fundamentals, design patterns, and object-oriented programming for teaching purposes.

## 📚 Project Structure

- **[TP1](tp1/)** — Fundamentals & Design Patterns (POJOs, immutability, factories, builders, Lombok)
- **[TP2](tp2/)** — Interfaces & Polymorphism (inheritance, composition, delegation)
- **[Report Aggregate](report-aggregate/)** — Consolidated quality metrics (JaCoCo, etc.)

## 🌐 Complete Documentation

**👉 [View Full Documentation & Reports](https://ebpro.github.io/java101-tp1/)**

The project website includes:
- 📖 Detailed learning guides for each module
- 📚 Complete API documentation (Javadoc)
- 📊 Code coverage reports (JaCoCo)
- ✅ Code quality analysis (Checkstyle, PMD, SpotBugs)
- 🧪 Test reports and source cross-references

## 🚀 Quick Start

### Prerequisites
```bash
java -version   # Requires Java 21+
./mvnw -version # Maven wrapper included
```

### Build & Test
```bash
# Build all modules
./mvnw clean package

# Run tests
./mvnw test

# Generate site with all reports
./build-site.sh --with-tests
```

### Run Examples
```bash
# Run TP1 examples
java -jar tp1/target/tp1-0.0.1-SNAPSHOT-withdependencies.jar

# Or use Maven
./mvnw -pl tp1 exec:java
```

## 🧪 Code Quality

### Git Hooks (Recommended)
Install pre-commit hooks for automatic validation:
```bash
./install-hooks.sh
```

Features: compile & test validation, commit message format check, pre-push verification.

### CI/CD Pipelines
- **CI**: Build & test on multiple Java versions
- **CodeQL**: Security analysis
- **Pages**: Automated site deployment

## 📖 Learning Resources

### For Students
1. **[TP1 Guide](https://ebpro.github.io/java101-tp1/tp1/)** — Learn basic patterns (factory, builder, value objects)
2. **[TP2 Guide](https://ebpro.github.io/java101-tp1/tp2/)** — Master OOP concepts (interfaces, polymorphism, composition)
3. **[Tests](tp1/src/test/)** — See patterns in action with JUnit 5 & AssertJ

### For Instructors
Professional development practices demonstrated:
- Multi-module Maven architecture with aggregation
- Comprehensive testing strategies
- Automated documentation & reporting
- CI/CD integration with GitHub Actions

## 📊 Reports & Metrics

All reports are available on the [project site](https://ebpro.github.io/java101-tp1/):

- **[Aggregated Coverage](https://ebpro.github.io/java101-tp1/report-aggregate/jacoco-aggregate/index.html)** — Combined JaCoCo report
- **[TP1 Reports](https://ebpro.github.io/java101-tp1/tp1/project-reports.html)** — Tests, coverage, quality
- **[TP2 Reports](https://ebpro.github.io/java101-tp1/tp2/project-reports.html)** — Tests, coverage, quality

## 🔧 Project Configuration

- `pom.xml` — Maven parent with centralized dependencies & plugins
- `checkstyle.xml` — Code style rules (Google style adapted)
- `pmd-ruleset.xml` — Static analysis rules
- `lombok.config` — Lombok configuration
- `.githooks/` — Quality automation scripts

## 📄 License

[MIT License](LICENSE) — Free for educational use.

## 👥 Contributing

Contributions welcome! Please:
1. Fork the repository
2. Install Git hooks: `./install-hooks.sh`
3. Create a feature branch
4. Ensure all tests pass: `./mvnw clean verify`
5. Submit a pull request

## 🙏 About

Created for teaching Java programming at **University of Toulon** by [Emmanuel Bruno](mailto:emmanuel.bruno@univ-tln.fr).

---

**📚 Complete guides, examples, and reports:**
**[https://ebpro.github.io/java101-tp1/](https://ebpro.github.io/java101-tp1/)**

