package demo.mathapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TaskAnswer {

    @EmbeddedId
    private TaskStudentKey id;

    @MapsId("taskId")
    @JoinColumn(name = "TASK_ID")
    @ManyToOne
    private Task task;

    @MapsId("studentId")
    @JoinColumn(name = "STUDENT_ID")
    @ManyToOne
    private Student student;

    private String answer;

}
