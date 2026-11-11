#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/backend"
mkdir -p data
if ! command -v java >/dev/null 2>&1; then echo "Java is not on PATH. Configure JAVA_HOME first."; exit 1; fi
if ! command -v mvn >/dev/null 2>&1; then echo "Maven is not on PATH."; exit 1; fi
java -version
mvn spring-boot:run
