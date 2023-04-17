package demo.mathapp.DTO.TestResult;

import demo.mathapp.DTO.Student.GetStudentTestInfo;
import demo.mathapp.DTO.TestAnswer.GetTestAnswer;
import lombok.Data;

import java.util.List;

@Data
public class GetTestResult {
    private GetStudentTestInfo student;
    private double score;
    private List<GetTestAnswer> answers;
}
