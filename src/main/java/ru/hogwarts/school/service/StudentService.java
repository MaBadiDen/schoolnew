package ru.hogwarts.school.service;


import ru.hogwarts.school.model.Student;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;

import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudent(long id) {
        return studentRepository.findById(id).get();
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }


    public Collection<Student> filterByAge(int min, int max) {
        return studentRepository.findStudentsByAgeBetween(min, max);
    }
}
