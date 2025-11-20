#!/usr/bin/env bash
set -euo pipefail
shopt -s nullglob

# build-site.sh - generate aggregated Maven site for all TP modules
# Usage: ./build-site.sh [--with-tests|--skip-tests] [--modules tp1,tp3,...|--all] [--staging-dir path]

ROOT_DIR=$(cd "$(dirname "$0")" && pwd)
cd "$ROOT_DIR"

WITH_TESTS="false"
MODULES_ARG=""
STAGING_DIR="target/staging"

function usage() {
  cat <<EOF
Usage: $0 [--with-tests|--skip-tests] [--modules tp1,tp2,... | --all] [--staging-dir path]

Options:
  --with-tests     Run full build (tests + reports) and aggregate JaCoCo
  --skip-tests     Fast build (no tests). Default behaviour when omitted.
  --modules        Comma-separated list of modules (e.g. tp1,tp3). Default: auto-detect tp* and TP* directories.
  --all            Include all modules discovered (same as auto-detect).
  --staging-dir    Destination directory for aggregated site (default: target/staging)
  -h|--help        Show this help
EOF
}

# parse args
while [[ $# -gt 0 ]]; do
  case "$1" in
    --with-tests)
      WITH_TESTS="true"; shift ;;
    --skip-tests)
      WITH_TESTS="false"; shift ;;
    --modules)
      MODULES_ARG="$2"; shift 2 ;;
    --all)
      MODULES_ARG="ALL"; shift ;;
    --staging-dir)
      STAGING_DIR="$2"; shift 2 ;;
    -h|--help)
      usage; exit 0 ;;
    *)
      echo "Unknown argument: $1" >&2; usage; exit 2 ;;
  esac
done

JACOCO_VERSION="0.8.14"

echo "$(date +%H:%M:%S) - Starting build-site.sh"
if [[ "$WITH_TESTS" == "true" ]]; then
  echo "🧪 Full build with tests: JaCoCo aggregation will be produced"
else
  echo "⚡ Fast build without tests"
fi

# discover modules matching tp* and TP* (only those that have a pom.xml)
DISCOVERED_MODULES=()
for d in tp* TP*; do
  # when nullglob is enabled, unmatched pattern is removed; but if the literal remains, skip
  if [[ "$d" == "tp*" || "$d" == "TP*" ]]; then
    continue
  fi
  if [[ -d "$d" && -f "$d/pom.xml" ]]; then
    DISCOVERED_MODULES+=("$d")
  fi
done

echo "Discovered modules: ${DISCOVERED_MODULES[*]}"

# If user passed --modules with a comma list, use that
MODULES=()
if [[ -n "$MODULES_ARG" && "$MODULES_ARG" != "ALL" ]]; then
  IFS=',' read -r -a provided <<< "$MODULES_ARG"
  for m in "${provided[@]}"; do
    # Trim whitespace
    m_trimmed=$(echo "$m" | sed 's/^\s*//;s/\s*$//')
    if [[ -d "$m_trimmed" && -f "$m_trimmed/pom.xml" ]]; then
      MODULES+=("$m_trimmed")
    else
      echo "Warning: requested module '$m_trimmed' not found or has no pom.xml, skipping." >&2
    fi
  done
elif [[ "$MODULES_ARG" == "ALL" ]]; then
  MODULES=("${DISCOVERED_MODULES[@]:-}")
else
  MODULES=("${DISCOVERED_MODULES[@]:-}")
fi

# Always include known helper modules if present (config, report-aggregate)
for m in config report-aggregate; do
  if [[ -d "$m" && -f "$m/pom.xml" ]]; then
    MODULES+=("$m")
  fi
done

# Remove duplicates while preserving order
# declare -A seen
# UNIQUE_MODULES=()
# for m in "${MODULES[@]:-}"; do
#   if [[ -n "$m" && -z "${seen[$m]:-}" ]]; then
#     UNIQUE_MODULES+=("$m")
#     seen[$m]=1
#   fi
# done
# MODULES=("${UNIQUE_MODULES[@]:-}")
# POSIX-compatible dedup (avoid associative arrays for macOS / older bash)
UNIQUE_MODULES=()
contains() {
  local needle="$1"; shift
  for el in "$@"; do
    if [[ "$el" == "$needle" ]]; then
      return 0
    fi
  done
  return 1
}
for m in "${MODULES[@]:-}"; do
  if [[ -n "$m" ]]; then
    if ! contains "$m" "${UNIQUE_MODULES[@]:-}"; then
      UNIQUE_MODULES+=("$m")
    fi
  fi
done
MODULES=("${UNIQUE_MODULES[@]:-}")

# Robustly compute module count even when `MODULES` might be unset (set -u is enabled)
if [[ "${MODULES+x}" = "x" ]]; then
  MODULE_COUNT=${#MODULES[@]}
else
  MODULE_COUNT=0
fi

if (( MODULE_COUNT == 0 )); then
  echo "No modules detected (tp*/TP*). Nothing to build." >&2
  exit 1
fi

echo "Modules to consider: ${MODULES[*]}"

echo "(debug) Final MODULES list: ${MODULES[*]}"

# Decide whether to skip PMD/CPD if ruleset missing
PMD_RULESET_PATH="${ROOT_DIR}/pmd-ruleset.xml"
PMD_SKIP_ARRAY=()
if [[ ! -f "$PMD_RULESET_PATH" ]]; then
  echo "Note: pmd-ruleset.xml not found at $PMD_RULESET_PATH — PMD/CPD reports will be skipped"
  PMD_SKIP_ARRAY+=("-Dpmd.skip=true" "-Dcpd.skip=true")
fi

# Build command assembly
MVN_CMD=("./mvnw")
MVN_EXTRA=()
if [[ "$WITH_TESTS" == "true" ]]; then
  MVN_GOALS=("-U" "clean" "verify" "org.jacoco:jacoco-maven-plugin:${JACOCO_VERSION}:report-aggregate" "site")
else
  MVN_GOALS=("clean" "site")
  MVN_EXTRA+=("-DskipTests=true")
fi

# Combine command as array safely (avoid referencing unset arrays with set -u)
CMD=()
CMD+=("${MVN_CMD[@]}")
CMD+=("${MVN_GOALS[@]}")
if [[ ${#MVN_EXTRA[@]:-0} -gt 0 ]]; then
  CMD+=("${MVN_EXTRA[@]}")
fi
if [[ ${#PMD_SKIP_ARRAY[@]:-0} -gt 0 ]]; then
  CMD+=("${PMD_SKIP_ARRAY[@]}")
fi

# Generate a fragment under src/site/includes/auto-modules.xml (do NOT overwrite site.xml by default)
generate_site_fragment() {
  INCLUDES_DIR="${ROOT_DIR}/src/site/includes"
  FRAGMENT_FILE="$INCLUDES_DIR/auto-modules.xml"
  mkdir -p "$INCLUDES_DIR"

  # Build list of learning modules (tp* / TP*), preserve order
  LEARNING_MODULES=()
  for d in tp* TP*; do
    if [[ "$d" == "tp*" || "$d" == "TP*" ]]; then
      continue
    fi
    if [[ -d "$d" && -f "$d/pom.xml" ]]; then
      LEARNING_MODULES+=("$d")
    fi
  done

  echo "(info) Generating site fragment $FRAGMENT_FILE with modules: ${LEARNING_MODULES[*]}"

  # Write fragment containing only <item> entries (no wrapping <menu>). The marker in site.xml
  # should be inside the <menu name="Learning Modules"> ... <!-- AUTO_MODULES --> ... </menu>
  cat > "$FRAGMENT_FILE" <<EOF
<!-- AUTO-GENERATED: module items only -->
EOF

  for m in "${LEARNING_MODULES[@]:-}"; do
    TITLE="$m"
    if [[ -f "$m/pom.xml" ]]; then
      # portable extraction of <name> from pom.xml using sed (works on macOS/BSD)
      pomname=$(sed -n 's:.*<name>\(.*\)</name>.*:\1:p; q' "$m/pom.xml" 2>/dev/null || true)
      if [[ -n "$pomname" ]]; then
        TITLE="$pomname"
      fi
    fi
    # write a single-level item (no nested children)
    cat >> "$FRAGMENT_FILE" <<EOF
    <item name="$TITLE" href="./$m/index.html"/>
EOF
  done

  # add a short note as an XML comment (no menu wrappers)
  cat >> "$FRAGMENT_FILE" <<EOF
<!-- Note: per-module report menus intentionally omitted from the global navigation. -->
EOF

  echo "(info) Generated fragment at $FRAGMENT_FILE"

  # If a site.xml exists, inject the fragment before </body> (auto-insert marker if absent)
  SITE_FILE="${ROOT_DIR}/src/site/site.xml"
  MARKER='<!-- AUTO_MODULES -->'
  if [[ -f "$SITE_FILE" ]]; then
    BACKUP="$SITE_FILE.bak"
    cp -f "$SITE_FILE" "$BACKUP"
    echo "(info) Backed up existing site.xml to $BACKUP"

    # Remove previous autogenerated comment markers to avoid accumulating duplicates
    if grep -q "AUTO-GENERATED" "$SITE_FILE" 2>/dev/null; then
      echo "(info) Removing previous AUTO-GENERATED comment lines from $SITE_FILE"
      awk '!/AUTO-GENERATED/ && !/per-module report menus intentionally omitted/' "$SITE_FILE" > "$SITE_FILE.tmp" && mv "$SITE_FILE.tmp" "$SITE_FILE"
    fi

    # Remove any existing <menu name="Learning Modules">...</menu> blocks to avoid duplication
    while grep -q '<menu name="Learning Modules">' "$SITE_FILE"; do
      echo "(info) Removing existing Learning Modules block from $SITE_FILE"
      awk '
        BEGIN{skip=0}
        {
          if(skip==0 && $0 ~ /<menu name="Learning Modules">/) { skip=1; next }
          else if(skip==1) { if($0 ~ /<\/menu>/) { skip=0; next } else next }
          else print
        }
      ' "$SITE_FILE" > "$SITE_FILE.tmp" && mv "$SITE_FILE.tmp" "$SITE_FILE"
    done

    if grep -qF "$MARKER" "$SITE_FILE"; then
      echo "(info) site.xml contains marker; injecting fragment items at marker"
      # Replace marker with fragment items (keep surrounding <menu> wrapper intact)
      awk -v fragfile="$FRAGMENT_FILE" -v marker="$MARKER" '
        { if(index($0,marker)){ while((getline line < fragfile) > 0) print line; next } print $0 }
      ' "$SITE_FILE" > "$SITE_FILE.tmp" && mv "$SITE_FILE.tmp" "$SITE_FILE"
      echo "(info) Injected auto-module items into $SITE_FILE at marker"
    else
      # insert a <menu name="Learning Modules"> wrapper and inject items before closing </body>
      echo "(info) site.xml does not contain marker; inserting menu wrapper with fragment before </body>"
      awk -v fragfile="$FRAGMENT_FILE" '
        { if(tolower($0) ~ "</body>"){ print "    <menu name=\"Learning Modules\">"; while((getline line < fragfile) > 0) print line; print "    </menu>"; print $0; next } print $0 }
      ' "$SITE_FILE" > "$SITE_FILE.tmp" && mv "$SITE_FILE.tmp" "$SITE_FILE"
      echo "(info) Injected auto-module menu (wrapper + items) into $SITE_FILE before </body>"
    fi
  else
    # No site.xml — create a minimal one that includes the fragment content inline (non-destructive)
    SITE_DIR="${ROOT_DIR}/src/site"
    mkdir -p "$SITE_DIR"
    cat > "$SITE_FILE" <<EOF
<?xml version="1.0" encoding="UTF-8"?>
<site>
  <skin>
    <groupId>org.apache.maven.skins</groupId>
    <artifactId>maven-fluido-skin</artifactId>
    <version>2.1.0</version>
  </skin>
  <body>
    <menu name="Overview">
      <item name="Home" href="./index.html"/>
    </menu>
    <menu name="Learning Modules">
      <!-- AUTO_MODULES -->
EOF
    # inject the fragment item contents inside the menu
    sed -n '1,200p' "$FRAGMENT_FILE" >> "$SITE_FILE"
    cat >> "$SITE_FILE" <<EOF
    </menu>
  </body>
</site>
EOF
    echo "(info) No existing site.xml — created minimal site.xml including the fragment items"
  fi
}

# Generate fragment (and inject if marker present)
generate_site_fragment

# Now run Maven
echo "Running Maven: ${CMD[*]}"
# execute safely as array
"${CMD[@]}"

# Aggregate sites into staging dir
echo "Aggregating module sites into $STAGING_DIR"
mkdir -p "$STAGING_DIR"

# copy root site if exists
if [[ -d target/site ]]; then
  echo "Copying root site -> $STAGING_DIR/"
  cp -a target/site/. "$STAGING_DIR/" || true
else
  echo "Root site not found (target/site) - skipping root copy"
fi

# After the build, look for any module that has a generated site and copy it.
# This is robust even if MODULES didn't include every tp*.
COPY_MODULES=()
for d in tp* TP* config report-aggregate; do
  # skip literal when no match
  if [[ "$d" == "tp*" || "$d" == "TP*" ]]; then
    continue
  fi
  if [[ -d "$d" && -d "$d/target/site" ]]; then
    COPY_MODULES+=("$d")
  fi
done

echo "(debug) Modules with generated sites: ${COPY_MODULES[*]}"

# copy module sites into staging/<module>
for m in "${COPY_MODULES[@]:-}"; do
  SRC="$m/target/site"
  DEST="$STAGING_DIR/$m"
  if [[ -d "$SRC" ]]; then
    echo "Copying $SRC -> $DEST"
    mkdir -p "$DEST"
    cp -a "$SRC/." "$DEST/" || true
  else
    echo "Warning: site for module '$m' not found at $SRC — skipping"
  fi
done

echo "Aggregation complete. Staging dir contents:"
ls -la "$STAGING_DIR" | sed -n '1,200p' || true

echo "To preview the aggregated site locally: open $STAGING_DIR/index.html"

if [[ "$WITH_TESTS" == "true" && -d "$STAGING_DIR/report-aggregate" ]]; then
  echo "Aggregated JaCoCo report: $STAGING_DIR/report-aggregate/jacoco-aggregate/index.html"
fi

echo "Done."
