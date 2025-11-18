# Aggregated Reports

This module consolidates quality metrics and coverage reports from all project modules.

## 📊 Coverage Reports

### Combined Code Coverage
**[JaCoCo Aggregated Report](./jacoco-aggregate/index.html)**

This report combines test coverage from all modules:
- **TP1**: Fundamentals & Design Patterns
- **TP2**: Interfaces & Polymorphism

**Metrics included:**
- **Instructions**: Bytecode instruction coverage
- **Branches**: Branch coverage for conditional logic
- **Lines**: Source line coverage
- **Methods**: Method execution coverage
- **Classes**: Class instantiation coverage

## 📈 Understanding Coverage

### What is Code Coverage?
Code coverage measures which parts of your codebase are executed during tests. Higher coverage generally indicates more thorough testing, but 100% coverage doesn't guarantee bug-free code.

### Coverage Metrics Explained

- **Instruction Coverage**: % of bytecode instructions executed
- **Branch Coverage**: % of decision branches (if/else, switch) tested
- **Line Coverage**: % of source code lines executed
- **Cyclomatic Complexity**: Measure of code complexity

### Good Coverage Goals

- **≥ 80%**: Good baseline for production code
- **≥ 90%**: Excellent coverage indicating thorough testing
- **100%**: Not always necessary or practical

**Focus on:**
- Critical business logic
- Complex algorithms
- Error handling paths
- Edge cases

## 🎯 Using These Reports

### For Students
1. **Explore the [aggregated JaCoCo report](./jacoco-aggregate/index.html)**
2. Look for **red/yellow sections** (uncovered code)
3. Check **branch coverage** for conditional logic
4. Review **missed complexity** for untested paths

### For Instructors
- Assess overall project test quality
- Identify modules needing more tests
- Compare coverage across modules
- Track coverage trends over time

## 📦 Module-Specific Reports

For detailed per-module analysis:

- **[TP1 Reports](../tp1/project-reports.html)** — Individual TP1 metrics
- **[TP2 Reports](../tp2/project-reports.html)** — Individual TP2 metrics

## 🔍 Additional Quality Metrics

While this module focuses on aggregated coverage, each module provides:

- **Test Results**: Success/failure rates
- **Checkstyle**: Code style compliance
- **PMD**: Code quality issues
- **CPD**: Code duplication detection
- **SpotBugs**: Potential bug detection

## 🚀 Generating Reports

To regenerate these aggregated reports:

```bash
# From project root
./build-site.sh --with-tests

# View aggregated report
open target/staging/report-aggregate/jacoco-aggregate/index.html
```

**Note**: Tests must be run before aggregation for accurate coverage data.

## 📚 Learn More

- [JaCoCo Documentation](https://www.jacoco.org/jacoco/trunk/doc/)
- [Maven Site Plugin](https://maven.apache.org/plugins/maven-site-plugin/)
- [Testing Best Practices](../index.html)

---

**[← Back to Main Site](../index.html)**

