#!/usr/bin/env bash
# Generate includes/auto-modules.xml from the parent POM modules list.
# Portable: uses only tools commonly available in CI images (sed/awk/bash).
set -euo pipefail
REPO_ROOT="$(cd "$(dirname "$0")/.." && pwd)"
POM="$REPO_ROOT/pom.xml"
OUT="$REPO_ROOT/src/site/includes/auto-modules.xml"

if [ ! -f "$POM" ]; then
  echo "Parent POM not found at $POM" >&2
  exit 1
fi

modules=()

# If xmlstarlet is available use it (more robust XML api), otherwise fallback to a portable sed/awk pipeline
if command -v xmlstarlet >/dev/null 2>&1; then
  while IFS= read -r m; do modules+=("$m"); done < <(xmlstarlet sel -t -m '//project/modules/module' -v . -n "$POM")
else
  # Portable extraction: capture the <modules>...</modules> block then extract <module> values
  modules_block=$(sed -n '/<modules>/,/<\/modules>/p' "$POM" || true)
  if [ -n "$modules_block" ]; then
    # iterate over lines and extract module entries using sed (POSIX-compatible)
    while IFS= read -r line; do
      mod=$(printf "%s" "$line" | sed -n 's/.*<module>[[:space:]]*\([^<][^<]*\)[[:space:]]*<\/module>.*/\1/p' || true)
      if [ -n "$mod" ]; then
        modules+=("$mod")
      fi
    done <<EOF
$modules_block
EOF
  fi
fi

if [ ${#modules[@]} -eq 0 ]; then
  echo "No modules found in $POM" >&2
  exit 1
fi

# render include fragment
mkdir -p "$(dirname "$OUT")"
{
  echo "<!-- AUTO-GENERATED: module items only -->"
  for m in "${modules[@]}"; do
    display="$m"
    # friendly display for tpX modules -> TPX
    if printf "%s" "$m" | sed -n 's/^tp\([0-9]\+\)$/\1/p' >/dev/null 2>&1; then
      # extract number
      num=$(printf "%s" "$m" | sed -n 's/^tp\([0-9]\+\)$/\1/p')
      display="TP${num}"
    fi
    printf '    <item name="%s" href="./%s/index.html"/>\n' "$display" "$m"
  done
  echo "<!-- Note: per-module report menus intentionally omitted from the global navigation. -->"
} > "$OUT"

echo "Wrote $OUT"
