package demo.mathapp.DTO.TestAnswer;

import demo.mathapp.model.Task;
import lombok.Data;

@Data
public class CreateTestAnswer {
    private String answer;
    private Task task;
}
