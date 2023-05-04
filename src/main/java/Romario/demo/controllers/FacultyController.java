package Romario.demo.controllers;

import Romario.demo.dto.FacultyDTO;
import Romario.demo.dto.StudentDTO;
import Romario.demo.models.Faculty;
import Romario.demo.services.FacultyService;
import Romario.demo.services.StudentService;
import Romario.demo.services.UniversityService;
import Romario.demo.util.ErrorResponse;
import Romario.demo.util.Exceptions.IncorrectFacultyException;
import Romario.demo.util.Exceptions.IncorrectUniversityException;
import Romario.demo.util.Exceptions.ObjectNotSavedException;
import Romario.demo.util.FacultyValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;
    private final UniversityService universityService;
    private final StudentService studentService;
    private final FacultyValidator facultyValidator;

    @Autowired
    public FacultyController(FacultyService facultyService, UniversityService universityService, StudentService studentService, FacultyValidator facultyValidator) {
        this.facultyService = facultyService;
        this.universityService = universityService;
        this.studentService = studentService;
        this.facultyValidator = facultyValidator;
    }

    @GetMapping
    public Set<FacultyDTO> getFaculties() {
        return facultyService.getAll().stream().map(FacultyController::toDto).collect(Collectors.toSet());
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid FacultyDTO facultyDTO,
                                          BindingResult bindingResult) {
        Faculty faculty = fromDTO(facultyDTO);
        facultyValidator.validate(faculty, bindingResult);

        if (bindingResult.hasErrors())
            ErrorResponse.returnErrors(bindingResult);

        facultyService.save(faculty);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/students")
    public Set<StudentDTO> getStudents(@RequestBody FacultyDTO facultyDTO) {
        Faculty faculty = facultyService.getByName(facultyDTO.getName()).orElse(null);

        if (faculty == null) {
            throw new IncorrectFacultyException("There is no such a faculty!");
        }

        return studentService.getByFaculty(faculty).stream()
                .map(StudentController::toDTO).collect(Collectors.toSet());
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static FacultyDTO toDto(Faculty faculty) {
        return new FacultyDTO(faculty);
    }

    private Faculty fromDTO(FacultyDTO facultyDTO) {
        Faculty faculty = new Faculty();

        faculty.setName(facultyDTO.getName());
        faculty.setFoundationYear(facultyDTO.getFoundationYear());
        faculty.setBudgetPlaces(facultyDTO.getBudgetPlaces());
        faculty.setIndependent(facultyDTO.getIndependent());

        faculty.setStudents(new HashSet<>());
        faculty.setUniversity(universityService.getByName(facultyDTO.getUniversity()).orElse(null));

        return faculty;
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleObjectNotSaved(ObjectNotSavedException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleIncorrectFaculty(IncorrectFacultyException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}