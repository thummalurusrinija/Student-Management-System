# Student Management System (Java Swing + JDBC + MySQL)

## Prerequisites
- Java 21+
- Maven 3.8+
- MySQL 8.x server

## Setup database
```sql
SOURCE sql/schema.sql;
```
Or manually:
1. Create DB and tables using `sql/schema.sql`.
2. Ensure a MySQL user has privileges to the `student_management` database.

## Configure application
Edit `src/main/resources/db.properties`:
```
db.url=jdbc:mysql://localhost:3306/student_management?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
db.username=root
db.password=changeme
```

## Build
```bash
mvn -DskipTests package
```

## Run
```bash
java -jar target/student-management-1.0-SNAPSHOT.jar
```

## Project layout
- `com.example.sms.model`: POJOs for Students, Courses, Marks, Attendance
- `com.example.sms.dao`: DAO interfaces
- `com.example.sms.dao.jdbc`: JDBC implementations
- `com.example.sms.config`: DB config and connection manager
- `com.example.sms.ui`: Swing UI (Main frame and panels)

## Next steps
- Implement Students CRUD actions (hook buttons to DAO)
- Add grade calculation and simple reports
- Add search and filtering integration