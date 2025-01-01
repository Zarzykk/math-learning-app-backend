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

    public String getClassName() {
        return classYear + classIndex;
    }
}
