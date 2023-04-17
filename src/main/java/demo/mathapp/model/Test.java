package demo.mathapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Test {

    public Test(double testTime, int maxTries, Date activationTime, Date deactivationTime, SchoolClass schoolClass, List<Task> tasks, List<TestResult> results) {
        this.testTime = testTime;
        this.maxTries = maxTries;
        this.activationTime = activationTime;
        this.deactivationTime = deactivationTime;
        this.schoolClass = schoolClass;
        this.tasks = new ArrayList<>();
        this.results = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private double testTime;
    @NotNull
    private int maxTries;
    private double maxPoints;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date activationTime;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date deactivationTime;
    @ManyToOne
    private SchoolClass schoolClass;
    @OneToMany(mappedBy = "test",fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    private List<Task> tasks;
    @OneToMany(mappedBy = "test",fetch = FetchType.LAZY,cascade = {CascadeType.MERGE})
    private List<TestResult> results;
}
