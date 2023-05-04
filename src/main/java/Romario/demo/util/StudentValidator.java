package Romario.demo.util;

import Romario.demo.models.Student;
import Romario.demo.services.FacultyService;
import Romario.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class StudentValidator implements Validator {
    private final StudentService studentService;
    private final FacultyService facultyService;

    @Autowired
    public StudentValidator(StudentService studentService, FacultyService facultyService) {
        this.studentService = studentService;
        this.facultyService = facultyService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Student.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Student student = (Student) target;

        if (studentService.getByNameAndSurnameAndAge(student.getName(), student.getSurname()).isPresent()) {
            errors.rejectValue("name", "", "This student is already present!");
        }
        if (student.getFaculty() == null) {
            errors.rejectValue("faculty", "", "This faculty can't be found!");
        }
    }
}