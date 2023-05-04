package Romario.demo.dto;

import Romario.demo.models.Faculty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

import java.util.Date;

public class FacultyDTO {
    @NotEmpty(message = "name can't be empty!")
    private String name;

    @NotNull(message = "Year can't be null")
    private Date foundationYear;

    @Range(min = 0, message = "Incorrect value!")
    private int budgetPlaces;

    @NotNull(message = "can't be empty!")
    private boolean independent;

    @NotNull(message = "can't be empty!")
    private String university;

    public FacultyDTO() {}

    public FacultyDTO(Faculty faculty) {
        name = faculty.getName();
        foundationYear = faculty.getFoundationYear();
        budgetPlaces = faculty.getBudgetPlaces();
        independent = faculty.getIndependent();
        university = faculty.getUniversity().getName();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Date getFoundationYear() {
        return foundationYear;
    }
    public void setFoundationYear(Date foundationYear) {
        this.foundationYear = foundationYear;
    }

    public int getBudgetPlaces() {
        return budgetPlaces;
    }
    public void setBudgetPlaces(int budgetPlaces) {
        this.budgetPlaces = budgetPlaces;
    }

    public boolean getIndependent() {
        return independent;
    }
    public void setIndependent(boolean independent) {
        this.independent = independent;
    }

    public String getUniversity() {
        return university;
    }
    public void setUniversity(String university) {
        this.university = university;
    }
}