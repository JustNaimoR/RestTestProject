package Romario.demo.controllers;

import Romario.demo.dto.FacultyDTO;
import Romario.demo.dto.UniversityDTO;
import Romario.demo.models.University;
import Romario.demo.services.FacultyService;
import Romario.demo.services.UniversityService;
import Romario.demo.util.ErrorResponse;
import Romario.demo.util.Exceptions.IncorrectUniversityException;
import Romario.demo.util.Exceptions.ObjectNotSavedException;
import Romario.demo.util.UniversityValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/university")
public class UniversityController {
    private final UniversityService universityService;
    private final FacultyService facultyService;
    private final UniversityValidator universityValidator;

    @Autowired
    public UniversityController(UniversityService universityService, FacultyService facultyService, UniversityValidator universityValidator) {
        this.universityService = universityService;
        this.facultyService = facultyService;
        this.universityValidator = universityValidator;
    }

    @GetMapping()
    public Set<UniversityDTO> getUniversities() {
        return universityService.getAll().stream().map(UniversityController::toDTO).collect(Collectors.toSet());
    }

    @GetMapping("/faculties")
    public Set<FacultyDTO> getFaculties(@RequestBody UniversityDTO universityDTO) {
        University university = universityService.getByName(universityDTO.getName()).orElse(null);

        if (university == null) {
            throw new IncorrectUniversityException("There is no such university!");
        }

        return facultyService.getByUniversity(university).stream()
                .map(FacultyController::toDto).collect(Collectors.toSet());
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody  @Valid UniversityDTO universityDTO,
                                          BindingResult bindingResult) {
        University university = fromDTO(universityDTO);
        universityValidator.validate(university, bindingResult);

        if (bindingResult.hasErrors())
            ErrorResponse.returnErrors(bindingResult);

        universityService.save(university);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static UniversityDTO toDTO(University university) {
        return new UniversityDTO(university);
    }

    private University fromDTO(UniversityDTO universityDTO) {
        University university = new University();

        university.setFoundationYear(universityDTO.getFoundationYear());
        university.setCity(universityDTO.getCity());
        university.setName(universityDTO.getName());
        university.setHasAccreditation(universityDTO.getHasAccreditation());

        university.setFaculties(new HashSet<>());

        return university;
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
    private ResponseEntity<ErrorResponse> handleIncorrectUniversity(IncorrectUniversityException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}