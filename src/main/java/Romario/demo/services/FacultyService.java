package Romario.demo.services;

import Romario.demo.models.Faculty;
import Romario.demo.models.University;
import Romario.demo.repositories.FacultyRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class FacultyService {
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Set<Faculty> getAll() {
        return new HashSet<>(facultyRepository.findAll());
    }

    @Transactional
    public void save(Faculty faculty) {
        facultyRepository.save(faculty);
    }

    public Optional<Faculty> getByName(String facultyName) {
        return facultyRepository.findByName(facultyName);
    }

    public Set<Faculty> getByUniversity(University university) {
        return new HashSet<>(facultyRepository.findAllByUniversity(university));
    }
}