# MediCare — Viva & Presentation Guide

## 7-slide outline
1. **Title:** MediCare — Smart Medicine Delivery Platform; team, department and guide.
2. **Problem & motivation:** Checking availability across stores is inconvenient; digital discovery can help.
3. **Objectives & scope:** Search, stock visibility, cart, demo order, AI platform assistant.
4. **Architecture & technology:** React/TypeScript → Spring Boot REST API → H2 embedded SQL database; Java chatbot service.
5. **Database & workflow:** medicines, customer_orders, order_items; 1:N order relationship; stock validation.
6. **Live demonstration:** Search → add to cart → place demo order → inspect DB → ask assistant.
7. **Conclusion & future scope:** benefits, limitations, authentication, prescription review, payment and delivery integrations.

## 90-second project explanation
“MediCare is a full-stack medicine delivery prototype. Its purpose is to make medicine catalogue discovery and availability checking more convenient. The frontend is built with React and TypeScript. It communicates with a Java Spring Boot backend through REST APIs. H2 embedded SQL database stores medicine inventory and customer order records. Users can search the catalogue, add eligible products to a cart and place a demo order. The backend checks stock and updates inventory transactionally. Prescription-required items are blocked from demo checkout. The AI assistant is implemented in Java and supports a configurable AI API, with a local FAQ fallback. It is restricted to platform support and general information, not diagnosis or prescribing.”

## Common viva questions
**Why Java for backend?** Java is strongly typed, widely used in enterprise systems, and Spring Boot provides mature REST, validation, persistence and security capabilities.

**Why H2 embedded SQL database?** It is a reliable open-source relational database with constraints, transactions and strong SQL support.

**Why TypeScript?** It adds static typing to JavaScript, helping catch frontend errors and making API data structures clearer.

**What is REST?** An architectural style for communication over HTTP using resources and methods such as GET and POST.

**What is JPA?** Java Persistence API; Spring Data JPA reduces boilerplate for mapping Java entities to relational tables.

**What happens when an order is placed?** The backend checks each item, validates stock and prescription flag, calculates total, reduces stock and saves the order and line items within a transaction.

**How is AI integrated?** Java's WebClient calls a configurable OpenAI-compatible endpoint. If no key is configured or the provider fails, a local FAQ fallback is used.

**Can it be used by real pharmacies today?** No. It is an educational prototype. Production use needs licensing, prescription review, security, privacy, payments, delivery integrations and regulatory compliance.

## Demo checklist
- Start H2 embedded SQL database and confirm `medicare` database exists.
- Start backend and test `http://localhost:8080/api/health`.
- Start frontend at `http://localhost:5173`.
- Search `paracetamol`.
- Add a non-prescription item and place a demo order.
- Show the reduced stock in the UI or database.
- Try the prescription-only sample and show the safety block.
- Ask the chatbot “How do I place an order?” and “Can you recommend a dose?”.
