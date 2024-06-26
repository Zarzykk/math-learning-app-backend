package demo.mathapp.model;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private int classYear;
    @NotNull
    private String classIndex;
    @OneToMany(mappedBy = "studentClass", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private List<Student> students = new ArrayList<>();
    @ManyToOne
    private Teacher teacher;
    @ManyToOne(fetch = FetchType.LAZY)
    private School school;
    @OneToMany(mappedBy = "schoolClass", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<Test> testList;
    @OneToMany(mappedBy = "schoolClass", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<Homework> homeworkList;
    @ManyToOne
    private Material material;


    public String getClassName() {
        return classYear + classIndex;
    }
}
