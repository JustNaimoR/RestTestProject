package Romario.demo.controllers;

import Romario.demo.dto.StudentDTO;
import Romario.demo.models.Student;
import Romario.demo.services.FacultyService;
import Romario.demo.services.StudentService;
import Romario.demo.util.ErrorResponse;
import Romario.demo.util.Exceptions.IncorrectStudentException;
import Romario.demo.util.Exceptions.IncorrectUniversityException;
import Romario.demo.util.Exceptions.ObjectNotSavedException;
import Romario.demo.util.StudentValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final FacultyService facultyService;
    private final StudentValidator studentValidator;

    @Autowired
    public StudentController(StudentService studentService, FacultyService facultyService, StudentValidator studentValidator) {
        this.studentService = studentService;
        this.facultyService = facultyService;
        this.studentValidator = studentValidator;
    }

    @GetMapping()
    public Set<StudentDTO> getStudents() {
        return studentService.getStudents().stream().map(StudentController::toDTO).collect(Collectors.toSet());
    }

    @GetMapping("/getLearnings")
    public Set<StudentDTO> getLearningStudents() {
        return studentService.getLearnings().stream().map(StudentController::toDTO).collect(Collectors.toSet());
    }

    @PostMapping("/changeData")
    public ResponseEntity<HttpStatus> changeData(@RequestBody @Valid StudentDTO studentDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            ErrorResponse.returnErrors(bindingResult);

        Student student = studentService.getByNameAndSurname(studentDTO.getName(), studentDTO.getSurname()).orElse(null);

        if (student == null) {
            throw new IncorrectStudentException("There is no such a student!");
        }

        studentService.update(student.getId(), fromDTO(studentDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid StudentDTO studentDTO,
                                          BindingResult bindingResult) {
        Student student = fromDTO(studentDTO);
        studentValidator.validate(student, bindingResult);

        if (bindingResult.hasErrors())
            ErrorResponse.returnErrors(bindingResult);

        studentService.save(student);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static StudentDTO toDTO(Student student) {
        return new StudentDTO(student);
    }

    private Student fromDTO(StudentDTO studentDTO) {
        Student student = new Student();

        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());
        student.setSurname(studentDTO.getSurname());
        student.setYearOfAdmission(studentDTO.getYearOfAdmission());
        student.setLearning(studentDTO.isLearning());

        student.setFaculty(facultyService.getByName(studentDTO.getFaculty()).orElse(null));

        return student;
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
    private ResponseEntity<ErrorResponse> handleIncorrectStudent(IncorrectStudentException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}