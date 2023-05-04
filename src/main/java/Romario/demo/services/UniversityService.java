package Romario.demo.services;

import Romario.demo.models.University;
import Romario.demo.repositories.UniversityRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class UniversityService {
    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public Set<University> getAll() {
        return new HashSet<>(universityRepository.findAll());
    }

    @Transactional
    public void save(University university) {
        universityRepository.save(university);
    }

    public Optional<University> getByName(String name) {
        return universityRepository.findByName(name);
    }

}