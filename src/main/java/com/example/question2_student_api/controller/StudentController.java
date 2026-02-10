package com.example.question2_student_api.controller;

import com.example.question2_student_api.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private List<Student> students = new ArrayList<>();

    // Sample data added in constructor
    public StudentController() {
        students.add(new Student(1L, "Alice", "Johnson", "alice@example.com", "Computer Science", 3.8));
        students.add(new Student(2L, "Bob", "Smith", "bob@example.com", "Mathematics", 3.2));
        students.add(new Student(3L, "Charlie", "Brown", "charlie@example.com", "Computer Science", 3.5));
        students.add(new Student(4L, "Diana", "White", "diana@example.com", "Physics", 3.9));
        students.add(new Student(5L, "Ethan", "Black", "ethan@example.com", "Chemistry", 2.9));
    }

    // GET all students
    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }

    // GET student by ID
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        for (Student s : students) {
            if (s.getStudentId().equals(studentId)) {
                return new ResponseEntity<>(s, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // GET students by major
    @GetMapping("/major/{major}")
    public List<Student> getStudentsByMajor(@PathVariable String major) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getMajor().equalsIgnoreCase(major)) {
                result.add(s);
            }
        }
        return result;
    }

    // GET students filtered by minimum GPA
    @GetMapping("/filter")
    public List<Student> filterByGpa(@RequestParam Double gpa) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getGpa() >= gpa) {
                result.add(s);
            }
        }
        return result;
    }

    // POST new student
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        students.add(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // PUT update student
    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(studentId)) {
                students.set(i, updatedStudent);
                return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
