package Romario.demo.dto;

import Romario.demo.models.University;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class UniversityDTO {
    @NotEmpty(message = "can't be empty!")
    private String name;

    @NotEmpty(message = "can't be empty!")
    private String city;

    @NotNull
    private boolean hasAccreditation;

    @NotNull
    private Date foundationYear;

    public UniversityDTO() {}

    public UniversityDTO(University university) {
        name = university.getName();
        city = university.getCity();
        hasAccreditation = university.getHasAccreditation();
        foundationYear = university.getFoundationYear();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public boolean getHasAccreditation() {
        return hasAccreditation;
    }
    public void setHasAccreditation(boolean hasAccreditation) {
        this.hasAccreditation = hasAccreditation;
    }

    public Date getFoundationYear() {
        return foundationYear;
    }
    public void setFoundationYear(Date foundationYear) {
        this.foundationYear = foundationYear;
    }
}
