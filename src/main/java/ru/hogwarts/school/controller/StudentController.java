package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {



    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student student = studentService.getStudent(id);
        if(student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Student> editFaculty(@PathVariable Long id, @RequestBody Student student) {
        Student foundStudent = studentService.editStudent(id, student);
        return ResponseEntity.ok(foundStudent);
    }
    @GetMapping("/filter")
    public Collection<Student> filterByAge(@RequestParam(required = true) int min,
                                           @RequestParam(required = true) int max) {
        return studentService.filterByAge(min, max);
    }

    @GetMapping("{id}/faculty")
    public Faculty getStudentFaculty(@PathVariable Long id) {
        return studentService.getStudentFaculty(id);
    }


}
