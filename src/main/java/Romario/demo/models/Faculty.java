package Romario.demo.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "faculty")
public class Faculty {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "foundation_year")
    @Temporal(TemporalType.DATE)
    private Date foundationYear;

    @Column(name = "budget_places")
    private int budgetPlaces;

    @Column(name = "independent")
    private boolean independent;

    @OneToMany(mappedBy = "id")
    private Set<Student> students;

    @ManyToOne
    @JoinColumn(name = "university_id", referencedColumnName = "id")
    private University university;

    public Faculty() {}

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

    public University getUniversity() {
        return university;
    }
    public void setUniversity(University university) {
        this.university = university;
    }

    public Set<Student> getStudents() {
        return students;
    }
    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
