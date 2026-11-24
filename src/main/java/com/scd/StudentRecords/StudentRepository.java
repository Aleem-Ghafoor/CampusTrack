
package com.scd.studentrecords;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    private final JdbcTemplate jdbc;

    public StudentRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Student> studentRowMapper = (rs, rowNum) -> new Student(
            rs.getInt("id"),
            rs.getString("reg_no"),
            rs.getString("name"),
            rs.getString("department"),
            rs.getDouble("cgpa")
    );

    public List<Student> findAll() {
        return jdbc.query(
                "SELECT * FROM students ORDER BY name",
                studentRowMapper
        );
    }


    public List<Student> searchByName(String name) {
        return jdbc.query(
                "SELECT * FROM students WHERE LOWER(name) LIKE LOWER(?) ORDER BY name",
                studentRowMapper,
                "%" + name + "%"
        );
    }


    public Student findByRegNo(String regNo) {

        List<Student> students = jdbc.query(
                "SELECT * FROM students WHERE reg_no = ?",
                studentRowMapper,
                regNo
        );

        if (students.isEmpty()) {
            return null;
        }

        return students.get(0);
    }


    public boolean existsByRegNo(String regNo) {

        Integer count = jdbc.queryForObject(
                "SELECT COUNT(*) FROM students WHERE reg_no = ?",
                Integer.class,
                regNo
        );

        return count != null && count > 0;
    }


    public int insert(Student s) {

        return jdbc.update(
                "INSERT INTO students (reg_no, name, department, cgpa) VALUES (?, ?, ?, ?)",
                s.getRegNo(),
                s.getName(),
                s.getDepartment(),
                s.getCgpa()
        );
    }


    public int update(Student s) {

        return jdbc.update(
                "UPDATE students SET name = ?, department = ?, cgpa = ? WHERE reg_no = ?",
                s.getName(),
                s.getDepartment(),
                s.getCgpa(),
                s.getRegNo()
        );
    }


    public int deleteByRegNo(String regNo) {

        return jdbc.update(
                "DELETE FROM students WHERE reg_no = ?",
                regNo
        );
    }


}
