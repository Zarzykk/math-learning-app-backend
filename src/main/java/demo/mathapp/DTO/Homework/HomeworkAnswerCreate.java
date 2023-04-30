package demo.mathapp.DTO.Homework;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HomeworkAnswerCreate {
    private double points;
    private String answer;
    //TODO: sprawdzić jak się zachowa, czy powiąże answer z homework
//    private long homeworkResultId;
//    private long taskId;
}
