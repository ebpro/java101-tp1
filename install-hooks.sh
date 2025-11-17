#!/bin/bash
#
# Install Git hooks for Java 101 TP1 project
#
# This script copies hooks from .githooks/ to .git/hooks/
# and makes them executable
#

set -e

HOOKS_DIR=".githooks"
GIT_HOOKS_DIR=".git/hooks"

echo "🔧 Installing Git hooks for Java 101 TP1..."
echo ""

# Check if we're in the project root
if [ ! -d ".git" ]; then
    echo "❌ Error: Not in a git repository root!"
    echo "Please run this script from the project root directory."
    exit 1
fi

if [ ! -d "$HOOKS_DIR" ]; then
    echo "❌ Error: $HOOKS_DIR directory not found!"
    exit 1
fi

# Create hooks directory if it doesn't exist
mkdir -p "$GIT_HOOKS_DIR"

# Install each hook
for hook in "$HOOKS_DIR"/*; do
    if [ -f "$hook" ]; then
        hook_name=$(basename "$hook")

        # Skip README and other non-hook files
        if [[ "$hook_name" == "README.md" ]]; then
            continue
        fi

        echo "📝 Installing $hook_name..."
        cp "$hook" "$GIT_HOOKS_DIR/$hook_name"
        chmod +x "$GIT_HOOKS_DIR/$hook_name"
        echo "   ✅ $hook_name installed and made executable"
    fi
done

echo ""
echo "✨ Git hooks successfully installed!"
echo ""
echo "Installed hooks:"
echo "  • pre-commit  - Compiles code and runs tests before each commit"
echo "  • commit-msg  - Validates commit message format (Conventional Commits)"
echo "  • pre-push    - Runs full verification before pushing"
echo ""
echo "To uninstall hooks, delete files from .git/hooks/"
echo "To temporarily skip hooks, use: git commit --no-verify"

