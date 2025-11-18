#!/usr/bin/env bash
set -euo pipefail

# regenerate_preview_index.sh
# Generates preview-sites.md and preview-sites.html in the specified outdir.
# Usage: regenerate_preview_index.sh [outdir] [--dry-run]
# Environment variables used if not provided as args:
#   NETLIFY_TOKEN, GITHUB_REPO, PREFIX, DRY_RUN

NETLIFY_TOKEN=${NETLIFY_TOKEN:-}
GITHUB_REPO=${GITHUB_REPO:-}
PREFIX=${PREFIX:-}
OUTDIR=${1:-preview-site}

# parse optional --dry-run
DRY_RUN_FLAG=false
for arg in "$@"; do
  case "$arg" in
    --dry-run) DRY_RUN_FLAG=true ;;
  esac
done
DRY_RUN=${DRY_RUN:-$DRY_RUN_FLAG}

if [ -z "$NETLIFY_TOKEN" ]; then
  echo "NETLIFY_TOKEN is required" >&2
  exit 1
fi
if [ -z "$GITHUB_REPO" ]; then
  echo "GITHUB_REPO is required" >&2
  exit 1
fi
if [ -z "$PREFIX" ]; then
  # derive prefix from repo name (owner/repo)
  REPO_NAME="${GITHUB_REPO#*/}"
  PREFIX="$(echo "$REPO_NAME" | tr 'A-Z' 'a-z' | sed 's/[^a-z0-9-]/-/g')-"
fi

# check dependencies
if ! command -v curl >/dev/null 2>&1; then
  echo "curl is required. Please install curl on the runner." >&2
  exit 1
fi
if ! command -v jq >/dev/null 2>&1; then
  echo "jq is required. Please install jq on the runner." >&2
  exit 1
fi

mkdir -p "$OUTDIR"
OUT_MD="$OUTDIR/preview-sites.md"
OUT_HTML="$OUTDIR/preview-sites.html"

# Fetch sites with pagination (per_page=100)
PAGE=1
PER_PAGE=100
SITES_JSON_CONCAT="[]"
while :; do
  RESP=$(curl -s -H "Authorization: Bearer ${NETLIFY_TOKEN}" "https://api.netlify.com/api/v1/sites?per_page=${PER_PAGE}&page=${PAGE}")
  # break if empty array
  COUNT=$(echo "$RESP" | jq 'length')
  if [ "$COUNT" -eq 0 ]; then
    break
  fi
  # merge arrays
  SITES_JSON_CONCAT=$(jq -s '.[0] + .[1]' <(echo "$SITES_JSON_CONCAT") <(echo "$RESP"))
  PAGE=$((PAGE+1))
  # safety guard
  if [ "$PAGE" -gt 50 ]; then
    echo "Pagination limit reached (page > 50), stopping." >&2
    break
  fi
done

SITES_JSON="$SITES_JSON_CONCAT"

if [ "$DRY_RUN" = true ] || [ "$DRY_RUN" = "true" ]; then
  echo "Running in DRY_RUN mode — no files will be committed or pushed."
fi

# Markdown
cat > "$OUT_MD" <<EOF
# Netlify previews for ${GITHUB_REPO}

_Regenerated: $(date -u +%Y-%m-%dT%H:%M:%SZ) UTC_

| Site | Branch | Preview | Admin | Badge | Last Updated |
|------|--------|---------|-------|-------|--------------|
EOF

# iterate and append rows
echo "$SITES_JSON" | jq -c '.[] | select(.name | test("^'${PREFIX}'"))' | while read -r SITE; do
  NAME=$(echo "$SITE" | jq -r '.name')
  ID=$(echo "$SITE" | jq -r '.id')
  URL="https://${NAME}.netlify.app"
  ADMIN_URL="https://app.netlify.com/sites/${NAME}/deploys"
  BADGE_URL="https://api.netlify.com/api/v1/badges/${ID}/deploy-status.svg"
  LAST_UPDATED=$(echo "$SITE" | jq -r '.updated_at')
  BRANCH_PART=${NAME#${PREFIX}}
  BRANCH=${BRANCH_PART%-preview}
  printf "| [%s](%s) | `%s` | [Preview](%s) | [Admin](%s) | ![Netlify Status](%s) | %s |\n" "$NAME" "$URL" "$BRANCH" "$URL" "$ADMIN_URL" "$BADGE_URL" "$LAST_UPDATED" >> "$OUT_MD"
done

cat >> "$OUT_MD" <<EOF

_Note: This file is auto-generated. Edit at your own risk._
EOF

# HTML header
cat > "$OUT_HTML" <<'HTML_EOF'
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Netlify Preview Sites</title>
  <meta name="viewport" content="width=device-width,initial-scale=1" />
  <style>
    body { font-family: system-ui,-apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,Ubuntu,sans-serif; margin: 2rem; background: #f7f9fc; color:#222; }
    h1 { margin-top:0; }
    table { border-collapse: collapse; width:100%; background:#fff; box-shadow:0 2px 4px rgba(0,0,0,.08); }
    th, td { padding: .6rem .8rem; border-bottom:1px solid #e3e8ef; text-align:left; font-size:.9rem; }
    th { background:#eef3f9; font-weight:600; }
    tr:hover { background:#f5f9ff; }
    code { background:#eef3f9; padding:2px 4px; border-radius:4px; font-size:.8rem; }
    .badge img { height:20px; vertical-align:middle; }
    footer { margin-top:2rem; font-size:.75rem; color:#555; }
  </style>
</head>
<body>
  <h1>Netlify Preview Sites</h1>
  <p>Ephemeral environments for feature branches and pull requests. Updated: <strong id="generated"></strong></p>
  <table id="previewTable">
    <thead>
      <tr>
        <th>Site</th><th>Branch</th><th>Preview</th><th>Admin</th><th>Status</th><th>Last Updated</th>
      </tr>
    </thead>
    <tbody>
HTML_EOF

# append rows
echo "$SITES_JSON" | jq -c '.[] | select(.name | test("^'${PREFIX}'"))' | while read -r SITE; do
  NAME=$(echo "$SITE" | jq -r '.name')
  ID=$(echo "$SITE" | jq -r '.id')
  URL="https://${NAME}.netlify.app"
  ADMIN_URL="https://app.netlify.com/sites/${NAME}/deploys"
  BADGE_URL="https://api.netlify.com/api/v1/badges/${ID}/deploy-status.svg"
  LAST_UPDATED=$(echo "$SITE" | jq -r '.updated_at')
  BRANCH_PART=${NAME#${PREFIX}}
  BRANCH=${BRANCH_PART%-preview}
  printf "    <tr><td><a href=\"%s\">%s</a></td><td><code>%s</code></td><td><a href=\"%s\">View</a></td><td><a href=\"%s\">Admin</a></td><td class=\"badge\"><img src=\"%s\" alt=\"status\"/></td><td>%s</td></tr>\n" "$URL" "$NAME" "$BRANCH" "$URL" "$ADMIN_URL" "$BADGE_URL" "$LAST_UPDATED" >> "$OUT_HTML"
done

# finish html
cat >> "$OUT_HTML" <<'HTML_EOF'
    </tbody>
  </table>
  <footer>
    Generated automatically by GitHub Actions.
  </footer>
  <script>
    document.getElementById('generated').textContent = new Date().toISOString();
  </script>
</body>
</html>
HTML_EOF

# no execute bit on generated files
# chmod +x "$OUTDIR"/* || true

echo "Generated $OUT_MD and $OUT_HTML (DRY_RUN=$DRY_RUN)"

if [ "$DRY_RUN" = true ] || [ "$DRY_RUN" = "true" ]; then
  echo "DRY_RUN enabled: not committing or pushing changes."
fi
