package Romario.demo.dto;

import Romario.demo.models.Student;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public class StudentDTO {

    @NotEmpty(message = "name can't be empty!")
    private String name;

    @NotEmpty(message = "surname can't be empty!")
    private String surname;

    @Min(value = 18, message = "Too young!")
    private int age;

    @Range(min = 1800, max = 2100, message = "Incorrect year!")
    private int yearOfAdmission;

    @NotNull(message = "can't be null")
    private boolean learning;

    @NotNull(message = "can't be empty!")
    private String faculty;

    public StudentDTO() {}

    public StudentDTO(Student student) {
        name = student.getName();
        surname = student.getSurname();
        age = student.getAge();
        yearOfAdmission = student.getYearOfAdmission();
        learning = student.isLearning();
        faculty = student.getFaculty().getName();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public int getYearOfAdmission() {
        return yearOfAdmission;
    }
    public void setYearOfAdmission(int yearOfAdmission) {
        this.yearOfAdmission = yearOfAdmission;
    }

    public String getFaculty() {
        return faculty;
    }
    public void setFaculty(String facultyName) {
        this.faculty = facultyName;
    }

    public boolean isLearning() {
        return learning;
    }
    public void setLearning(boolean learning) {
        this.learning = learning;
    }
}