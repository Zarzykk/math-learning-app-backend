package demo.mathapp.service;

import demo.mathapp.model.TestAnswer;
import demo.mathapp.model.TestResult;
import demo.mathapp.repository.TestAnswerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TestAnswerService {

    private final TestAnswerRepository testAnswerRepository;
    private final ModelMapper modelMapper;


    public void setAnswers(List<TestAnswer> answers, long id) {
        TestResult result = new TestResult();
        result.setId(id);
        for (TestAnswer answer: answers) {
            answer.setResult(result);
        }
        testAnswerRepository.saveAll(answers);
    }
}
