# 🎓 Student Management System

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![License: MIT](https://img.shields.io/badge/License-MIT-blue)
![Java](https://img.shields.io/badge/Java-17-orange)

## 📘 Overview
The Student Management System is a simple web application built to manage student data efficiently.  
It allows users to perform CRUD operations on student records while showcasing the core concepts of Spring Boot, MVC, and Security.

## 🚀 Features
- Add, edit, and delete student records  
- View all student details  
- User authentication with Spring Security  
- In-memory H2 database for development/testing  

## 🛠️ Tech Stack
- **Backend:** Spring Boot 3+, Spring MVC, Spring Data JPA  
- **Security:** Spring Security (Basic Authentication)  
- **Frontend:** Thymeleaf, HTML, CSS  
- **Database:** H2 In-Memory Database  
- **Build Tool:** Maven  

## ⚙️ Project Setup
1. **Clone the repository**
   ```bash
   git clone https://github.com/<your-username>/student-management-system.git
   cd student-management-system
   ```
2. **Build the project**
   ```bash
   mvn clean install
   ```
3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```
4. Open in browser
   ```bash
   http://localhost:8080/
   ```
   
## 🧠 Folder Structure
```
student-management-system/
├── src/
│   ├── main/
│   │   ├── java/com/student/Management/System/
│   │   │   ├── controller/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   └── config/
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── index.html
│   │       │   └── student-form.html
│   │       └── application.properties
├── pom.xml
└── README.md
```

## 🧩 Future Enhancements
- Add role-based authorization (Admin / User)
- Implement student search and pagination
- Integrate MySQL database
- Add REST API endpoints for external integration

## 👨‍💻 Author
**Chirag Chaudhary**  
📧 [chirag9171@gmail.com](mailto:chirag9171@gmail.com)  
🌐 [GitHub Profile](https://github.com/chiragchaudhary)
