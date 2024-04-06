package demo.mathapp.service.impl;

import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.TestAnswer;
import demo.mathapp.repository.TestAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestAnswerService {
    private final TestAnswerRepository testAnswerRepository;

    public TestAnswer createAnswer(TestAnswer answer) {
        return testAnswerRepository.save(answer);
    }

    public void deleteAnswer(Long id) {
        testAnswerRepository.deleteById(id);
    }

    public TestAnswer updateAnswer(Long id, TestAnswer answer) {
        TestAnswer oldAnswer = (TestAnswer) testAnswerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found"));
        oldAnswer.setAnswer(answer.getAnswer());
        oldAnswer.setPoints(answer.getPoints());
        return testAnswerRepository.save(oldAnswer);
    }
}
