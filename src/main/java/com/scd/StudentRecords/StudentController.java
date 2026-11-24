
package com.scd.studentrecords;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 1. GET /students
    // Show all students or search by name
    @GetMapping
    public String getAllStudents(
            @RequestParam(required = false) String name,
            Model model) {

        List<Student> students;

        if (name == null || name.isBlank()) {
            students = studentService.getAllStudents();
        } else {
            students = studentService.searchStudents(name);
        }

        model.addAttribute("students", students);
        model.addAttribute("searchName", name);

        return "students";
    }

    // 2. GET /students/{regNo}
    // Show student details
    @GetMapping("/{regNo}")
    public String getStudentByRegNo(
            @PathVariable String regNo,
            Model model) {

        Student student = studentService.getStudentByRegNo(regNo);

        if (student == null) {
            model.addAttribute("message", "Student not found");
        } else {
            model.addAttribute("student", student);
        }

        return "student-details";
    }

    // 3. GET /students/new
    // Open blank student form
    @GetMapping("/new")
    public String showNewStudentForm(Model model) {

        model.addAttribute("student", new Student());

        return "student-form";
    }

    // 4. POST /students
    // Create new student
    @PostMapping
    public String addStudent(
            @RequestParam String regNo,
            @RequestParam String name,
            @RequestParam String department,
            @RequestParam Double cgpa) {

        Student student = new Student(
                0,
                regNo,
                name,
                department,
                cgpa
        );

        studentService.addStudent(student);

        return "redirect:/students";
    }

    // 5. GET /students/{regNo}/edit
    // Open edit form with current values
    @GetMapping("/{regNo}/edit")
    public String showEditForm(
            @PathVariable String regNo,
            Model model) {

        Student student = studentService.getStudentByRegNo(regNo);

        if (student == null) {
            model.addAttribute("message", "Student not found");
            return "student-details";
        }

        model.addAttribute("student", student);

        return "student-form";
    }

    // 6. POST /students/{regNo}/update
    // Update student
    @PostMapping("/{regNo}/update")
    public String updateStudent(
            @PathVariable String regNo,
            @RequestParam String name,
            @RequestParam String department,
            @RequestParam Double cgpa) {

        Student student = new Student(
                0,
                regNo,
                name,
                department,
                cgpa
        );

        studentService.updateStudent(regNo, student);

        return "redirect:/students";
    }

    // 7. POST /students/{regNo}/delete
    // Delete student
    @PostMapping("/{regNo}/delete")
    public String deleteStudent(@PathVariable String regNo) {

        studentService.deleteStudent(regNo);

        return "redirect:/students";
    }
}
