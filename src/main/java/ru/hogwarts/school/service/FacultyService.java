package ru.hogwarts.school.service;


import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public FacultyService(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(long id) {

        return facultyRepository.findById(id).orElseThrow(FacultyNotFoundException::new);
    }

    public void deleteFaculty(long id) {
        getFaculty(id);
        facultyRepository.deleteById(id);
    }

    public Faculty editFaculty(long id, Faculty faculty) {
        Faculty oldFaculty = getFaculty(id);
        oldFaculty.setName(faculty.getName());
        oldFaculty.setColor(faculty.getColor());
        return facultyRepository.save(oldFaculty);
    }

    public Collection<Faculty> filterFacByNameOrColor(String colorOrName) {
        return facultyRepository.findFacultiesByNameIgnoreCaseOrColorIgnoreCase(colorOrName, colorOrName);
    }


    public Collection<Student> getStudentsByFacId(Long id) {

        return studentRepository.findStudentsByFaculty_Id(id);
    }
}
