package Romario.demo.repositories;

import Romario.demo.models.Faculty;
import Romario.demo.models.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    Optional<Faculty> findByName(String facultyName);
    List<Faculty> findAllByUniversity(University university);
}