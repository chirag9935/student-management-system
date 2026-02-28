# 🎓 Student Management System

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-brightgreen?logo=springboot)
![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![H2 Database](https://img.shields.io/badge/Database-H2%20In--Memory-blue)
![Security](https://img.shields.io/badge/Security-Spring%20Security-yellow?logo=springsecurity)
![License: MIT](https://img.shields.io/badge/License-MIT-lightgrey)

> A full-stack **Student Management Web Application** built with Spring Boot, Thymeleaf, Spring Security, and H2 Database. Features a modern dark-mode dashboard with CRUD operations, search, statistics, and role-based access control.

---

## ✨ Features

| Feature              | Description                                                    |
| -------------------- | -------------------------------------------------------------- |
| 🔐 Authentication    | Login/logout with Spring Security (form-based)                 |
| 👥 Role-Based Access | Admins can delete; Users can only view/edit                    |
| 📊 Dashboard         | Stats: total students, average marks, top scorer, passing rate |
| 🔍 Search            | Filter students by name, email, or course in real-time         |
| ✅ Validation        | Form validation with field-level error messages                |
| 🏅 Grade Badges      | Auto-computed letter grades (A/B/C/D/F) based on marks         |
| 🌙 Dark Mode UI      | Premium dark-mode design with gradient accents                 |
| 🗄️ REST API          | JSON endpoints for external integrations                       |
| 🛡️ Error Handling    | Custom error pages with friendly messages                      |

---

## 🛠️ Tech Stack

| Layer      | Technology                             |
| ---------- | -------------------------------------- |
| Backend    | Spring Boot 3.2, Spring MVC            |
| Security   | Spring Security 6 (form login, BCrypt) |
| ORM        | Spring Data JPA + Hibernate            |
| Frontend   | Thymeleaf + Vanilla CSS (dark-mode)    |
| Validation | Jakarta Bean Validation                |
| Database   | H2 In-Memory (auto-schema + seed data) |
| Build      | Maven                                  |

---

## 🗂️ Project Structure

```
Management-System/
├── src/
│   ├── main/
│   │   ├── java/com/student/Management/System/
│   │   │   ├── config/
│   │   │   │   └── SecurityConfig.java       ← Authentication & authorization rules
│   │   │   ├── controller/
│   │   │   │   ├── StudentController.java    ← REST API (JSON endpoints)
│   │   │   │   └── ViewController.java       ← Web UI (HTML page endpoints)
│   │   │   ├── entity/
│   │   │   │   └── Student.java              ← JPA entity / data model
│   │   │   ├── exception/
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   └── StudentNotFoundException.java
│   │   │   ├── repository/
│   │   │   │   └── StudentRepository.java    ← DB layer (CRUD + custom search)
│   │   │   ├── service/
│   │   │   │   └── StudentService.java       ← Business logic & statistics
│   │   │   └── ManagementSystemApplication.java
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── fragments/
│   │       │   │   └── navbar.html           ← Reusable navbar fragment
│   │       │   ├── index.html                ← Dashboard page
│   │       │   ├── login.html                ← Login page
│   │       │   ├── student-form.html         ← Add / Edit student form
│   │       │   └── error.html                ← Error page
│   │       ├── static/css/
│   │       │   └── style.css                 ← Premium dark-mode styles
│   │       ├── application.properties        ← App configuration
│   │       └── data.sql                      ← Sample student data
└── pom.xml
```

---

## ⚙️ Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+

### 1. Clone the repository

```bash
git clone https://github.com/chirag9935/student-management-system.git
cd student-management-system/Management-System
```

### 2. Build the project

```bash
mvn clean package -DskipTests
```

### 3. Run the application

```bash
mvn spring-boot:run
```

### 4. Open in browser

```
http://localhost:8080/
```

You'll be redirected to the login page automatically.

---

## 🔑 Login Credentials

| Username | Password   | Role  | Permissions                 |
| -------- | ---------- | ----- | --------------------------- |
| `admin`  | `admin123` | ADMIN | View, Add, Edit, **Delete** |
| `user`   | `user123`  | USER  | View, Add, Edit             |

---

## 🌐 REST API Endpoints

The app also exposes a REST API for external integrations. All endpoints require authentication.

| Method | URL                         | Description                 |
| ------ | --------------------------- | --------------------------- |
| GET    | `/api/students`             | Get all students            |
| GET    | `/api/students?search=java` | Search by name/email/course |
| GET    | `/api/students/{id}`        | Get student by ID           |
| POST   | `/api/students`             | Create a new student        |
| PUT    | `/api/students/{id}`        | Update a student            |
| DELETE | `/api/students/{id}`        | Delete a student            |

---

## 🗄️ H2 Database Console (Dev)

Access the H2 in-memory database browser at:

```
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:studentdb
Username: sa
Password: (leave empty)
```

---

## 🧩 Key Concepts Demonstrated

This project demonstrates several important Spring Boot patterns:

- **MVC Architecture** — Controller → Service → Repository layers
- **JPA / ORM** — Entities, repositories, JPQL queries
- **Spring Security** — Form login, BCrypt hashing, role-based access
- **Bean Validation** — `@NotBlank`, `@Email`, `@Min/@Max` with form error display
- **Thymeleaf Fragments** — Reusable navbar with `th:replace`
- **Flash Messages** — `RedirectAttributes` for post-redirect-get pattern
- **Custom Exceptions** — `StudentNotFoundException` + `@ControllerAdvice`
- **REST API** — JSON endpoints alongside HTML views

---

## 🚀 Future Enhancements

- [ ] MySQL / PostgreSQL integration (replace H2)
- [ ] Pagination for large datasets
- [ ] Export student list to PDF / Excel
- [ ] Student photo upload
- [ ] Email notifications

---

## 👨‍💻 Author

**Chirag Chaudhary**
📧 [chirag9171@gmail.com](mailto:chirag9171@gmail.com)
🌐 [GitHub Profile](https://github.com/chirag9935)
