package demo.mathapp.DTO.Homework;

import demo.mathapp.model.HomeworkResult;
import demo.mathapp.model.Task;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class HomeworkCreate {
    private Date deadline;
    private long schoolClassId;
    private List<HomeworkTaskCreate> tasks;
    private List<HomeworkResult> homeworkResults;

}
