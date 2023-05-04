package Romario.demo.models;

import Romario.demo.dto.UniversityDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "university")
public class University {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name can't be empty!")
    private String name;

    @Column(name = "city")
    @NotEmpty(message = "City can't be empty!")
    private String city;

    @Column(name = "has_accreditation")
    private boolean hasAccreditation;

    @Column(name = "foundation_year")
    @Temporal(TemporalType.DATE)
    private Date foundationYear;

    @OneToMany(mappedBy = "id")
    private Set<Faculty> faculties;

    public University() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    public Set<Faculty> getFaculties() {
        return faculties;
    }
    public void setFaculties(Set<Faculty> faculties) {
        this.faculties = faculties;
    }
}