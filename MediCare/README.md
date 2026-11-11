<<<<<<< HEAD
# MediCare — Smart Medicine Delivery Platform

A college mini-project demonstrating a full-stack medicine ordering workflow with:
- React + TypeScript + Vite + Tailwind CSS frontend
- Java 21 + Spring Boot REST API backend
- H2 local database with SQL schema and seed data
- Optional AI assistant integration through an OpenAI-compatible chat-completions API, with a safe local FAQ fallback

## Architecture
`frontend` calls `backend` over HTTP. The Spring Boot backend uses Spring Data JPA to access H2. The chatbot is implemented in Java; no Python runtime is required.

## Prerequisites
- JDK 21
- Maven 3.9+
- Node.js 20+
- H2 embedded SQL database (included as a Maven dependency)
- Git (optional)

## 1. Database setup (no separate installation)
The project uses H2, a lightweight embedded relational SQL database. You do not need H2, pgAdmin, psql, a database username setup, or a running database service. The backend creates a persistent local file at `backend/data/medicare.mv.db` on first start and creates tables through Hibernate.

The sample medicine catalogue is inserted automatically on first start. H2's browser console is available at `http://localhost:8080/h2-console` after the backend starts. Use JDBC URL `jdbc:h2:file:./data/medicare;DB_CLOSE_ON_EXIT=FALSE`, username `sa`, and leave password blank. Stop the backend before connecting with a separate H2 client. H2-compatible `database/schema.sql` and `database/seed.sql` are included as reference scripts.

## 2. Run the backend
```bash
cd backend
```
No database environment variables are required for the default local setup. Then:
```bash
mvn spring-boot:run
```
For backend tests, run `mvn test`. To build the executable JAR, run `mvn clean package`.
API base URL: `http://localhost:8080/api`

Health check: `GET http://localhost:8080/api/health`

## 3. Run the frontend
In a second terminal:
```bash
cd frontend
npm install
npm run dev
```
Open the URL printed by Vite, normally `http://localhost:5173`.

## Demo credentials / data
The seed script inserts medicines. For the mini-project demo, registration is available in the UI. Authentication is intentionally kept simple in this starter version; production deployment must add robust password hashing, JWT/session management, authorization checks and audited prescription workflows before handling real patient data or payments.

## AI assistant setup
Without an API key, the chatbot uses a local FAQ fallback. To enable a model, configure:
- `AI_API_URL` — OpenAI-compatible chat completions endpoint, e.g. `https://api.openai.com/v1/chat/completions`
- `AI_API_KEY` — API key
- `AI_MODEL` — model name supported by your provider

PowerShell:
```powershell
$env:AI_API_URL="https://api.openai.com/v1/chat/completions"
$env:AI_API_KEY="YOUR_KEY"
$env:AI_MODEL="YOUR_MODEL"
```
Restart backend. Never commit API keys. The assistant is informational only and must not diagnose, prescribe, or advise dose changes.

## Main API endpoints
| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/api/health` | Health check |
| GET | `/api/medicines` | List medicines |
| GET | `/api/medicines?search=para` | Search catalogue |
| GET | `/api/medicines/{id}` | Medicine details |
| POST | `/api/orders` | Place a demo order |
| GET | `/api/orders` | List recent orders |
| POST | `/api/chat` | Chatbot response |

## Suggested presentation flow
1. Explain the problem: fragmented medicine availability and confusing ordering.
2. Show architecture: React → REST API → Spring Boot → H2.
3. Browse/search medicines and inspect stock.
4. Add items and place a demo order.
5. Ask the AI assistant a safe general/platform question.
6. Show the SQL tables and explain relationships.
7. Discuss limitations and future scope (pharmacy onboarding, verified prescriptions, payment gateway, live delivery integration).

## Important scope and safety limitations
This is an educational prototype, not a licensed pharmacy or production healthcare system. Do not use real patient data. Do not dispense prescription-only medicines without a valid prescription and qualified review. Do not rely on AI for clinical decisions. Add privacy, security, regulatory, payment and operational controls before any real-world deployment.
=======
# Medicine-Delivery-WebApp
>>>>>>> b23c9d394c7564333edab80005aa7b9316805bab
