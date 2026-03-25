# Getting Started

## Prerequisites

- Java 17+
- Maven 3.8+
- IntelliJ IDEA (or any IDE with Maven support)
- [Postman](https://www.postman.com/downloads/)

---

## 1. Clone the Repository

```bash
git clone https://github.com/your-org/your-repo.git
cd your-repo
```

---

## 2. Resync Maven Dependencies

### IntelliJ IDEA
1. Open the project in IntelliJ.
2. Right-click `pom.xml` → **Maven** → **Reload Project**
   *(or click the **Maven** panel → refresh icon)*

### CLI
```bash
mvn dependency:resolve
# or force update snapshots/releases
mvn clean install -U
```

---

## 3. Run the Application

### Via IDE
Run the main class annotated with `@SpringBootApplication` (or equivalent entry point).

### Via Maven
```bash
mvn spring-boot:run
```

### Via JAR
```bash
mvn clean package
java -jar target/your-app.jar
```

The application starts on `http://localhost:8080` by default.

---

## 4. Import the Postman Collection

1. Open **Postman**.
2. Click **Import** (top-left).
3. Select the collection file:
   ```
   postman/your-collection.json
   ```
4. Click **Import**.
5. Set the required environment variables (e.g., `base_url`, `token`) in your Postman environment if applicable.

---

## Notes

- Copy `.env.example` to `.env` and fill in your local values before running.
- Default port can be changed in `src/main/resources/application.properties`:
  ```properties
  server.port=8080
  ```
