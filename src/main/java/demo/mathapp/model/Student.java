package demo.mathapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = {CascadeType.ALL})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;
    @ManyToOne
    private SchoolClass studentClass;
    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    private List<HomeworkResult> homeworkResults;
    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    private List<TestResult> testResults;
}
