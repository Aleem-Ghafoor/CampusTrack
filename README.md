# Student Records Management System

A web-based Student Records Management System built with **Spring Boot, Java, MySQL, JdbcTemplate, and Thymeleaf**. The application provides a simple interface to manage student information and perform CRUD operations.

## Screenshots

### 1. Student Dashboard

![1795561594945](image/README/1795561594945.png)

### 2. Add New Student

![1795561579939](image/README/1795561579939.png)

### 3. Edit Student

![1795561562404](image/README/1795561562404.png)

## Features

- View all student records in a dashboard.
- Search students by name.
- View individual student details.
- Add new student records.
- Update existing student information.
- Delete student records.
- Validate required fields and prevent duplicate registration numbers.
- Validate CGPA within the range of 0.00 to 4.00.
- Store and retrieve records using MySQL and Spring JdbcTemplate.

## Technology Stack

| Technology   | Purpose                          |
| ------------ | -------------------------------- |
| Java 17      | Application programming language |
| Spring Boot  | Backend application framework    |
| Spring MVC   | Request handling and routing     |
| JdbcTemplate | Database operations              |
| MySQL        | Relational database              |
| Thymeleaf    | Server-side HTML templates       |
| HTML and CSS | User interface                   |

## Application Architecture

```text
Browser
   |
   v
StudentController
   |
   v
StudentService
   |
   v
StudentRepository (JdbcTemplate)
   |
   v
MySQL Database
```

## Database Setup

1. Start your MySQL server.
2. Create the database and table:

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

3. Configure your MySQL credentials in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/scd_records
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

Replace `YOUR_PASSWORD` with your local MySQL password. Do not commit real credentials to GitHub.

## Run the Application

From the project root directory, run:

**Windows**

```powershell
.\mvnw.cmd spring-boot:run
```

**macOS / Linux**

```bash
./mvnw spring-boot:run
```

Open the application in your browser:

[http://localhost:8080/students](http://localhost:8080/students)

## Main Routes

| Method | Endpoint                     | Description                     |
| ------ | ---------------------------- | ------------------------------- |
| GET    | `/students`                | List students or search by name |
| GET    | `/students/{regNo}`        | View student details            |
| GET    | `/students/new`            | Open the add-student form       |
| POST   | `/students`                | Create a student                |
| GET    | `/students/{regNo}/edit`   | Open the edit form              |
| POST   | `/students/{regNo}/update` | Update a student                |
| POST   | `/students/{regNo}/delete` | Delete a student                |

## Project Structure

```text
src/
 └── main/
     ├── java/com/scd/studentrecords/
     │   ├── Student.java
     │   ├── StudentController.java
     │   ├── StudentService.java
     │   ├── StudentRepository.java
     │   └── StudentrecordsApplication.java
     └── resources/
         ├── templates/
         │   ├── students.html
         │   ├── student-details.html
         │   └── student-form.html
         ├── static/css/
         │   ├── student.css
         │   ├── student-details.css
         │   └── student-form.css
         └── application.properties
```

## Notes

- The application uses **JdbcTemplate** for database access.
- Keep database credentials local and out of version control.
- Ensure MySQL is running and the database is configured before starting the application.
