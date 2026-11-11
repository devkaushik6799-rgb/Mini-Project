# MediCare — Smart Medicine Delivery Platform
## Mini Project Report (submission-ready draft)

### Abstract
MediCare is a web-based medicine catalogue and ordering prototype designed to make medicine discovery and ordering more convenient. The system provides a searchable catalogue, stock visibility, a cart and demo order placement. A Java Spring Boot REST API connects the frontend to a H2 embedded SQL database database. An informational AI assistant helps users understand platform workflows while explicitly avoiding diagnosis, prescriptions and dosage recommendations.

### 1. Problem Statement
People may need to contact multiple pharmacies or visit stores to check whether a product is available. The process can be inconvenient, especially for people with limited mobility or busy schedules. A digital platform can centralize product discovery and display stock information. However, healthcare commerce requires additional safeguards around prescription-only products, privacy and professional review.

### 2. Objectives
1. Build a clean, responsive and accessible medicine catalogue.
2. Allow users to search products and view current demo stock.
3. Implement a cart and transactional order workflow.
4. Store medicine and order information in a local relational database.
5. Expose backend functionality through Java REST APIs.
6. Integrate an AI assistant for non-clinical platform support.
7. Demonstrate basic prescription safety controls.

### 3. Proposed Solution
The application uses a three-tier architecture. The React/TypeScript client handles presentation and user interactions. Spring Boot validates requests, applies business rules and persists records through Spring Data JPA. H2 embedded SQL database stores catalogue, order and order-item records. The chatbot is implemented in Java and can use a configured OpenAI-compatible API; otherwise it responds using a local FAQ fallback.

### 4. Technology Stack
| Layer | Technology | Purpose |
|---|---|---|
| Frontend | React, TypeScript, Vite | Component-based UI and type safety |
| Styling | Tailwind CSS + custom CSS | Responsive formal interface |
| Backend | Java 21, Spring Boot 3 | REST services and business logic |
| Persistence | Spring Data JPA, Hibernate | ORM and database access |
| Database | H2 embedded SQL database | Relational local storage |
| Validation | Jakarta Bean Validation | Request validation |
| HTTP/AI | Spring WebFlux WebClient | Calling compatible AI endpoints |
| Monitoring | Spring Boot Actuator | Health endpoint |

### 5. System Architecture
```text
User / Browser
      |
      v
React + TypeScript Frontend (localhost:5173)
      |
      | HTTP / JSON REST
      v
Java Spring Boot Backend (localhost:8080)
      |             |
      v             v
Spring Data JPA   AI Chat Service
      |             |
      v             v
H2 embedded SQL database      Configured AI API
(local machine) or local FAQ fallback
```

### 6. Main Modules
- Catalogue module: list, search and inspect medicine records.
- Cart module: add/remove quantities and calculate an estimated total.
- Order module: validate stock, reject prescription-required items in the demo checkout, decrement stock and persist the order.
- AI assistant: answer platform questions and route clinical questions to qualified professionals.
- Database module: maintain normalized catalogue and order data.

### 7. Database Design
`medicines` stores product details, price, category, stock quantity and prescription requirement. `customer_orders` stores the customer-facing order record, total, status and timestamp. `order_items` stores each item in an order and snapshots its name and price at order time.

Relationship: one customer order has many order items (1:N). The medicine ID is retained in each line item as a catalogue reference; the name and unit price are snapshotted for historical display.

### 8. API Design
| Method | Route | Description |
|---|---|---|
| GET | `/api/health` | API health |
| GET | `/api/medicines` | Retrieve catalogue |
| GET | `/api/medicines?search=term` | Search catalogue |
| GET | `/api/medicines/{id}` | Retrieve one medicine |
| POST | `/api/orders` | Validate and place demo order |
| GET | `/api/orders` | List orders |
| POST | `/api/chat` | Send a message to assistant |

### 9. Real-World Relevance and Safety
The prototype demonstrates how a digital catalogue can reduce the effort required to discover product availability. It includes a prescription-required flag and blocks those items from demo checkout. A real service would require pharmacy licensing and onboarding, prescription validation by qualified pharmacists, secure handling of health data, user authentication, payment compliance, delivery operations and applicable legal/regulatory review. AI output must not be treated as medical advice.

### 10. Testing Plan
1. Verify API health endpoint.
2. Confirm catalogue loads from H2 embedded SQL database.
3. Search for a medicine by name.
4. Add an available non-prescription product to cart.
5. Place an order and verify an order row and order-item rows are stored.
6. Confirm stock quantity decreases after successful order.
7. Confirm order fails if requested quantity exceeds stock.
8. Confirm prescription-required product cannot be checked out.
9. Test chatbot FAQ and safe response for symptom/dosage prompts.
10. Test empty search, empty cart and backend-offline UI states.

### 11. Limitations
- Demo customer details are static; user registration and authentication are not implemented.
- No payment gateway or real delivery partner integration.
- Prescription upload and pharmacist review are described as future scope, not operational.
- No real-time pharmacy network or geolocation.
- AI API integration requires the user to provide a provider key; without it the FAQ fallback is used.
- This is not a production pharmacy service.

### 12. Future Scope
- Secure registration/login with Spring Security and JWT.
- Pharmacy onboarding and multi-pharmacy inventory.
- Prescription upload with encrypted storage and pharmacist verification.
- Order status lifecycle and delivery partner integration.
- Payment gateway sandbox integration.
- Notifications and medication reminder scheduling.
- Audit logs, role-based access control and privacy controls.
- AI retrieval from approved product leaflets and official health sources with citations and escalation rules.

### 13. Conclusion
MediCare demonstrates a practical full-stack healthcare-commerce workflow using a modern TypeScript frontend, Java Spring Boot backend and H2 embedded SQL database database. The prototype addresses medicine catalogue discovery and order processing while highlighting the safety, privacy and professional-review requirements of real healthcare delivery systems.
