package demo.mathapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class HomeworkAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double points;
    private String answer;

    @ManyToOne
    private HomeworkResult homeworkResult;
    @ManyToOne
    private Task task;
}
