# MediCare database — H2 Embedded SQL

MediCare uses H2 as a file-based relational SQL database. It is included as a Maven dependency; no database server or separate database installer is required.

- File: `backend/data/medicare.mv.db` (created automatically at first backend start)
- JDBC URL: `jdbc:h2:file:./data/medicare;DB_CLOSE_ON_EXIT=FALSE`
- Username: `sa`
- Password: blank for this local educational demo
- Browser console: `http://localhost:8080/h2-console` while backend is running

Tables: `MEDICINES`, `CUSTOMER_ORDERS`, and `ORDER_ITEMS`. Hibernate creates/updates tables from the JPA entities. `schema.sql` and `seed.sql` are reference scripts for the H2 schema and sample catalogue. The application startup seeder loads sample medicines only when the catalogue is empty.

Do not open the same file database concurrently from multiple independent processes. Stop the backend before opening external database tools.
