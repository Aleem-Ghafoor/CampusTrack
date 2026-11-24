<div align="center">

# 🎓 CampusTrack

### Student Records Management System

A clean, web-based application for managing student records with **Spring Boot, Java 17, MySQL, and Thymeleaf**.

<p>
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 17" />
  <img src="https://img.shields.io/badge/Spring_Boot-Framework-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL" />
  <img src="https://img.shields.io/badge/Thymeleaf-Templates-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white" alt="Thymeleaf" />
</p>

[Features](#-features) · [Screenshots](#-screenshots) · [Tech Stack](#-tech-stack) · [Setup](#-getting-started) · [Routes](#-application-routes)

</div>

---

## 📌 Overview

**CampusTrack** helps manage student information through a straightforward web interface. It supports the core student-record workflow: browse, search, view, add, update, and delete records.

## ✨ Features

| Feature | Description |
| --- | --- |
| 📋 Student Dashboard | View the available student records in one place |
| 🔎 Search | Find students by name |
| 👤 Student Details | Open an individual student's record |
| ➕ Create Records | Add a student with the required information |
| ✏️ Update Records | Edit an existing student record |
| 🗑️ Delete Records | Remove a student record |
| ✅ Validation | Require key fields and prevent duplicate registration numbers |
| 📊 CGPA Validation | Restrict CGPA values to the 0.00–4.00 range |
| 🗄️ MySQL Storage | Persist records through Spring JdbcTemplate |

## 🖥️ Screenshots

<p align="center">
  <strong>Student Dashboard</strong><br/>
  <img src="image/README/1795561594945.png" alt="CampusTrack student dashboard" width="90%" />
</p>

<p align="center">
  <strong>Add New Student</strong><br/>
  <img src="image/README/1795561579939.png" alt="CampusTrack add student form" width="90%" />
</p>

<p align="center">
  <strong>Edit Student</strong><br/>
  <img src="image/README/1795561562404.png" alt="CampusTrack edit student form" width="90%" />
</p>

## 🧰 Tech Stack

| Technology | Role |
| --- | --- |
| Java 17 | Programming language |
| Spring Boot | Application framework |
| Spring MVC | Web request handling |
| Spring JdbcTemplate | Database access |
| MySQL | Relational database |
| Thymeleaf | Server-side page rendering |
| HTML & CSS | User interface |
| Maven | Build and dependency management |

## 🏗️ Application Flow

The application follows a layered flow:

```text
       Browser
          │
          ▼
  StudentController
          │
          ▼
    StudentService
          │
          ▼
 StudentRepository
    (JdbcTemplate)
          │
          ▼
     MySQL Database
```

## 🚀 Getting Started

### Prerequisites

Make sure you have the following installed:

- Java 17
- MySQL Server
- Git

### 1. Clone the repository

```bash
git clone https://github.com/Aleem-Ghafoor/CampusTrack.git
cd CampusTrack
```

### 2. Create the database

Run the following SQL in MySQL:

```sql
CREATE DATABASE IF NOT EXISTS scd_records;
USE scd_records;

CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    reg_no VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(50) NOT NULL,
    cgpa DECIMAL(3,2) NOT NULL
);
```

### 3. Configure the database connection

Open `src/main/resources/application.properties` and set your local MySQL connection details:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/scd_records
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

> **Security note:** Replace the placeholder with your local password. Never commit real database credentials.

### 4. Run the application

**Windows (PowerShell):**

```powershell
.\mvnw.cmd spring-boot:run
```

**macOS / Linux:**

```bash
./mvnw spring-boot:run
```

### 5. Open CampusTrack

Visit **http://localhost:8080/students** in your browser.

## 🧭 Application Routes

| Method | Endpoint | Purpose |
| --- | --- | --- |
| `GET` | `/students` | List students or search by name |
| `GET` | `/students/{regNo}` | View student details |
| `GET` | `/students/new` | Open the add-student form |
| `POST` | `/students` | Create a student |
| `GET` | `/students/{regNo}/edit` | Open the edit form |
| `POST` | `/students/{regNo}/update` | Update a student |
| `POST` | `/students/{regNo}/delete` | Delete a student |

## 📁 Project Layout

```text
src/
├── main/
│   ├── java/com/scd/studentrecords/
│   │   ├── Student.java
│   │   ├── StudentController.java
│   │   ├── StudentService.java
│   │   ├── StudentRepository.java
│   │   └── StudentrecordsApplication.java
│   └── resources/
│       ├── templates/
│       ├── static/css/
│       └── application.properties
└── test/
```

## 📝 Notes

- MySQL must be running and the `scd_records` database must exist before launching the app.
- Database credentials should remain local and must not be pushed to GitHub.
- The application uses Spring JdbcTemplate for database operations.

---

<div align="center">

**CampusTrack** · Student Records Management System

<sub>Built with Java & Spring Boot</sub>

</div>
