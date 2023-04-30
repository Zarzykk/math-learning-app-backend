package demo.mathapp.model;

import demo.mathapp.ClassYear;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class SchoolClass {
    public SchoolClass(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private ClassYear classYear;
    @NotNull
    private String classIndex;
    @OneToMany(mappedBy = "studentClass", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private List<Student> students = new ArrayList<>();
    @ManyToOne
    private Teacher teacher;
    @ManyToOne(fetch = FetchType.LAZY)
    private School school;
    @OneToMany(mappedBy = "schoolClass", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<Test> tests;
    @OneToMany(mappedBy = "schoolClass", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<Homework> homeworkList;
    @ManyToOne
    private Material material;
}
