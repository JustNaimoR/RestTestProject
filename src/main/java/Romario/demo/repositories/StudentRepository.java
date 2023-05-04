package Romario.demo.repositories;

import Romario.demo.models.Faculty;
import Romario.demo.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByNameAndSurname(String name, String surname);
    List<Student> findAllByFaculty(Faculty faculty);
}
