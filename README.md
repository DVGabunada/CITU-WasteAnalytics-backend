# Getting Started

## Prerequisites

- Java 17+
- Maven 3.8+
- IntelliJ IDEA (or any IDE with Maven support)
- [Postman](https://www.postman.com/downloads/)

---

## 1. Clone the Repository

```bash
git clone https://github.com/DVGabunada/CITU-WasteAnalytics-backend
cd your-repo
```

---

## 2. Resync Maven Dependencies

### IntelliJ IDEA
1. Open the project in IntelliJ.
2. Right-click `pom.xml` → **Maven** → **Reload Project**
   *(or click the **Maven** panel → refresh icon)*
   
---

## 3. Run the Application

The application starts on `http://localhost:8080` by default.

---

## 4. Import the Postman Collection

1. Open **Postman**.
2. Click **Import** (top-left).
3. Select the collection file:
   ```
   Waste Analytics.postman_collection.json
   ```
4. Click **Import**.

---

## Notes

- Copy `.env.example` to `.env` and fill in your local values before running.
- Default port can be changed in `src/main/resources/application.properties`:
  ```properties
  server.port=8080
  ```
