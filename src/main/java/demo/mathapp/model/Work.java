package demo.mathapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "WORK_TYPE",
        discriminatorType = DiscriminatorType.STRING
)
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long maxWorkTime;
    private double maxPoints;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date activationTime;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date deactivationTime;
    @ManyToOne
    private SchoolClass schoolClass;
    @OneToMany(mappedBy = "work",fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    private List<Task> tasks;
    @OneToMany(mappedBy = "work",fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    private List<TestResult> testResults;
    @OneToMany(mappedBy = "work",fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    private List<HomeworkResult> homeworkResults;

}
