package demo.mathapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double score;
    @OneToMany(mappedBy = "result",fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    private List<TestAnswer> answers;
    @ManyToOne(cascade = {CascadeType.MERGE})
    private Test test;
    @ManyToOne(cascade = {CascadeType.MERGE})
    private Student student;
}
