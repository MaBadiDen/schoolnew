package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;


import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {



    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty addStudent(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.getFaculty(id);
        return ResponseEntity.ok(faculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Faculty> editFaculty(@PathVariable Long id, @RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(id, faculty);
        return ResponseEntity.ok(foundFaculty);
    }
    @GetMapping("/filter")
    public Collection<Faculty> filterByAge(@RequestParam(required = true) String nameOrColor) {
        return facultyService.filterFacByNameOrColor(nameOrColor);
    }
    @GetMapping("{id}/students")
    public Collection<Student> getFacStudents(@PathVariable Long id) {
        return facultyService.getStudentsByFacId(id);
    }
}
