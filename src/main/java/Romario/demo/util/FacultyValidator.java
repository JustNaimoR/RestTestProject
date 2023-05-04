package Romario.demo.util;

import Romario.demo.models.Faculty;
import Romario.demo.services.FacultyService;
import Romario.demo.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FacultyValidator implements Validator {
    private final FacultyService facultyService;
    private final UniversityService universityService;

    @Autowired
    public FacultyValidator(FacultyService facultyService, UniversityService universityService) {
        this.facultyService = facultyService;
        this.universityService = universityService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Faculty.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Faculty faculty = (Faculty) target;

        if (facultyService.getByName(faculty.getName()).isPresent()) {
            errors.rejectValue("name", "", "This faculty is already registered!");
        }
        if (faculty.getUniversity() == null) {
            errors.rejectValue("university", "", "There is no such university!");
        }
    }
}
