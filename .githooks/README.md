# Git Hooks for Java 101 TP1

This directory contains Git hooks to ensure code quality and consistent commit messages.

## Available Hooks

### 🔍 pre-commit
Runs before each commit to verify:
- ✅ Code compiles successfully
- ✅ All tests pass
- ⚠️  Javadoc warnings (informational only)

**Time:** ~5-10 seconds

### 📝 commit-msg
Validates commit message format according to [Conventional Commits](https://www.conventionalcommits.org/).

**Format:** `<type>(<scope>): <description>`

**Valid types:**
- `feat` - New feature
- `fix` - Bug fix
- `docs` - Documentation changes
- `style` - Code style/formatting
- `refactor` - Code refactoring
- `test` - Test additions/updates
- `chore` - Maintenance tasks
- `ci` - CI/CD changes
- `perf` - Performance improvements
- `build` - Build system changes

**Examples:**
```
feat(person): add factory method for Person creation
fix(address): correct validation in Address constructor
test: add tests for PersonValueObject
docs: update README with singleton pattern example
```

### 🚀 pre-push
Runs before pushing to remote:
- ✅ Clean build with all tests
- ✅ Full verification (includes integration tests if any)
- 🔍 Checks for TODO/FIXME markers (warning only)

**Time:** ~10-20 seconds

## Installation

Run from the project root:

```bash
./install-hooks.sh
```

This will:
1. Copy hooks from `.githooks/` to `.git/hooks/`
2. Make them executable
3. Activate them for your local repository

## Skipping Hooks

To temporarily skip hooks (use sparingly!):

```bash
# Skip pre-commit hook
git commit --no-verify -m "message"

# Skip pre-push hook
git push --no-verify
```

## Why Use Git Hooks?

### For Students 👨‍🎓
- **Learn best practices early**: Conventional commits, automated testing
- **Avoid common mistakes**: Committing broken code, poor commit messages
- **Build confidence**: Know your code works before sharing
- **Professional habits**: Industry-standard practices

### For Educators 👨‍🏫
- **Quality assurance**: All submitted code compiles and passes tests
- **Consistent format**: Easier to review commit history
- **Teaching tool**: Demonstrate CI/CD principles locally

## Troubleshooting

### Hook fails but code works
```bash
# Verify manually
./mvnw clean verify

# If that works, check hook permissions
ls -la .git/hooks/pre-commit
chmod +x .git/hooks/pre-commit
```

### Want to update hooks
Just run `./install-hooks.sh` again - it will overwrite existing hooks.

### Hooks too slow
Edit the hook files to:
- Skip tests in pre-commit: add `-DskipTests`
- Make pre-push lighter: remove `clean` from `mvn clean verify`

## Customization

Hooks are simple shell scripts in `.githooks/`. Feel free to modify them:

```bash
# Edit hooks
vim .githooks/pre-commit

# Reinstall
./install-hooks.sh
```

## See Also

- [Conventional Commits](https://www.conventionalcommits.org/)
- [Git Hooks Documentation](https://git-scm.com/docs/githooks)
- [Maven Build Lifecycle](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)

