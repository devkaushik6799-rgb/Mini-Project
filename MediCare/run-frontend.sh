#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/frontend"
if ! command -v node >/dev/null 2>&1 || ! command -v npm >/dev/null 2>&1; then echo "Node.js and npm must be on PATH."; exit 1; fi
if [ ! -d node_modules ]; then npm install; fi
npm run dev
