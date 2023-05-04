package Romario.demo.services;

import Romario.demo.models.Faculty;
import Romario.demo.models.Student;
import Romario.demo.repositories.StudentRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Set<Student> getStudents() {
        return new HashSet<>(studentRepository.findAll());
    }

    @Transactional
    public void save(Student student) {
        studentRepository.save(student);
    }

    public Optional<Student> getByNameAndSurnameAndAge(String name, String surname) {
        return studentRepository.findByNameAndSurname(name, surname);
    }

    public Set<Student> getByFaculty(Faculty faculty) {
        return new HashSet<>(studentRepository.findAllByFaculty(faculty));
    }
}