
package com.scd.studentrecords;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    // Get all students
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // Search students by name
    public List<Student> searchStudents(String name) {
        return repository.searchByName(name);
    }

    // Find student by registration number
    public Student getStudentByRegNo(String regNo) {
        return repository.findByRegNo(regNo);
    }

    // Check whether registration number already exists
    public boolean existsByRegNo(String regNo) {
        return repository.existsByRegNo(regNo);
    }

    // Add a new student
    public boolean addStudent(Student student) {

        if (student.getRegNo() == null || student.getRegNo().isBlank()) {
            return false;
        }

        if (repository.existsByRegNo(student.getRegNo())) {
            return false;
        }

        if (student.getName() == null || student.getName().isBlank()) {
            return false;
        }

        if (student.getDepartment() == null || student.getDepartment().isBlank()) {
            return false;
        }

        if (student.getCgpa() == null ||
                student.getCgpa() < 0 ||
                student.getCgpa() > 4.00) {
            return false;
        }

        return repository.insert(student) > 0;
    }

    // Update an existing student
    public boolean updateStudent(String regNo, Student student) {

        Student existingStudent = repository.findByRegNo(regNo);

        if (existingStudent == null) {
            return false;
        }

        if (student.getName() == null || student.getName().isBlank()) {
            return false;
        }

        if (student.getDepartment() == null || student.getDepartment().isBlank()) {
            return false;
        }

        if (student.getCgpa() == null ||
                student.getCgpa() < 0 ||
                student.getCgpa() > 4.00) {
            return false;
        }

        student.setRegNo(regNo);

        return repository.update(student) > 0;
    }

    // Delete a student
    public boolean deleteStudent(String regNo) {

        Student existingStudent = repository.findByRegNo(regNo);

        if (existingStudent == null) {
            return false;
        }

        return repository.deleteByRegNo(regNo) > 0;
    }
}
