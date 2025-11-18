#!/usr/bin/env zsh
# Script pour générer le site Maven agrégé avec tous les modules
# Usage: ./build-site.sh [--with-tests]

set -e

WITH_TESTS=""
if [[ "$1" == "--with-tests" ]]; then
    echo "🧪 Build avec tests (pour rapport JaCoCo agrégé)"
    WITH_TESTS="true"
else
    echo "⚡ Build rapide sans tests"
    WITH_TESTS="false"
fi

JACOCO_VERSION="0.8.14"

echo "🏗️  Génération des sites Maven..."
if [[ "$WITH_TESTS" == "true" ]]; then
    # Single Maven invocation: clean, verify (tests + module reports), aggregate JaCoCo, then site
    ./mvnw -U clean verify org.jacoco:jacoco-maven-plugin:${JACOCO_VERSION}:report-aggregate site
else
    ./mvnw clean site -DskipTests
fi

echo "📦 Agrégation des sites modules dans target/staging..."
mkdir -p target/staging
cp -r target/site/* target/staging/
cp -r tp1/target/site target/staging/tp1
cp -r tp2/target/site target/staging/tp2
cp -r report-aggregate/target/site target/staging/report-aggregate

echo "✅ Site agrégé créé dans target/staging"
echo ""
echo "📂 Contenu du staging:"
ls -la target/staging/ | head -30
echo ""
echo "🌐 Pour visualiser le site:"
echo "   open target/staging/index.html"
echo ""
if [[ "$WITH_TESTS" == "true" ]]; then
    echo "📊 Rapport JaCoCo agrégé disponible:"
    echo "   open target/staging/report-aggregate/jacoco-aggregate/index.html"
fi

