#!/usr/bin/env bash
# Script to generate aggregated Maven site for all modules
# Usage: ./build-site.sh [--with-tests|--skip-tests]

set -e

WITH_TESTS=""
if [[ "$1" == "--with-tests" ]]; then
    echo "🧪 Running build with tests (for aggregated JaCoCo report)"
    WITH_TESTS="true"
elif [[ "$1" == "--skip-tests" ]] || [[ -z "$1" ]]; then
    echo "⚡ Fast build without tests"
    WITH_TESTS="false"
else
    echo "❌ Usage: $0 [--with-tests|--skip-tests]"
    exit 1
fi

JACOCO_VERSION="0.8.14"

echo "🏗️  Generating Maven sites..."
if [[ "$WITH_TESTS" == "true" ]]; then
    # Single Maven invocation: clean, verify (tests + module reports), aggregate JaCoCo, then site
    ./mvnw -U clean verify org.jacoco:jacoco-maven-plugin:${JACOCO_VERSION}:report-aggregate site
else
    ./mvnw clean site -DskipTests
fi

echo "📦 Aggregating module sites into target/staging..."
mkdir -p target/staging
cp -r target/site/* target/staging/ || true
cp -r tp1/target/site target/staging/tp1 || true
cp -r tp2/target/site target/staging/tp2 || true
cp -r report-aggregate/target/site target/staging/report-aggregate || true

echo "✅ Aggregated site created in target/staging"

echo "📂 Staging contents:"
ls -la target/staging/ | head -30 || true

echo "🌐 To preview the site locally:"
echo "   open target/staging/index.html"

if [[ "$WITH_TESTS" == "true" ]]; then
    echo "📊 Aggregated JaCoCo report available:"
    echo "   open target/staging/report-aggregate/jacoco-aggregate/index.html"
fi
