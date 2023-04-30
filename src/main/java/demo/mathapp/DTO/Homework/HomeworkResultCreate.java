package demo.mathapp.DTO.Homework;

import demo.mathapp.model.Homework;
import demo.mathapp.model.HomeworkAnswer;
import demo.mathapp.model.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HomeworkResultCreate {
    private List<HomeworkAnswer> answers;
    private long studentId;
    private long homeworkId;
}
