package demo.mathapp.DTO.Homework;

import demo.mathapp.model.MaterialTopic;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HomeworkTaskCreate {
    private String text;
    private double points;
    private long homeworkId;
    private List<HomeworkAnswerCreate> answers;
    private long materialTopicId;
}
