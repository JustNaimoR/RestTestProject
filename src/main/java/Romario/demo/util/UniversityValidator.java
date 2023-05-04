package Romario.demo.util;

import Romario.demo.models.University;
import Romario.demo.services.UniversityService;
import jakarta.validation.ConstraintViolation;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UniversityValidator implements Validator {
    private final UniversityService universityService;

    @Autowired
    public UniversityValidator(UniversityService universityService) {
        this.universityService = universityService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return University.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        University university = (University) target;

        if (universityService.getByName(university.getName()).isPresent()) {
            errors.rejectValue("name", "", "This university is already exists!");
        }
    }

}