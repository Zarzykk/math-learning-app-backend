package demo.mathapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Task {
    public Task(String text, double points, Homework homework, List<HomeworkAnswer> homeworkAnswers, MaterialTopic materialTopic) {
        this.text = text;
        this.points = points;
        this.homework = homework;
        this.homeworkAnswers = homeworkAnswers;
        this.materialTopic = materialTopic;
    }

    public Task(String text, double points, Test test, List<TestAnswer> testAnswers, MaterialTopic materialTopic) {
        this.text = text;
        this.points = points;
        this.test = test;
        this.testAnswers = testAnswers;
        this.materialTopic = materialTopic;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String text;
    private double points;
    @ManyToOne
    private Test test;
    @ManyToOne
    private Homework homework;
    @OneToMany(mappedBy = "task",fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    private List<TestAnswer> testAnswers;
    @OneToMany(mappedBy = "task",fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    private List<HomeworkAnswer> homeworkAnswers;
    @OneToOne
    private MaterialTopic materialTopic;
}
