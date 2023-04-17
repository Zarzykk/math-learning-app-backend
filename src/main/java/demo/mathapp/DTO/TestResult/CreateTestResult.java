package demo.mathapp.DTO.TestResult;

import demo.mathapp.DTO.TestAnswer.CreateTestAnswer;
import demo.mathapp.model.Student;
import demo.mathapp.model.Test;
import lombok.Data;

import java.util.List;

@Data
public class CreateTestResult {
    private Student student;
    private Test test;
    private List<CreateTestAnswer> answers;
}
