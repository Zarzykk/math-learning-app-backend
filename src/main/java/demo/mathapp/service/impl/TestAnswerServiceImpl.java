package demo.mathapp.service.impl;

import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.TestAnswer;
import demo.mathapp.repository.TestAnswerRepository;
import demo.mathapp.service.TestAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestAnswerServiceImpl implements TestAnswerService {
    private final TestAnswerRepository testAnswerRepository;

    @Override
    public TestAnswer createAnswer(TestAnswer answer) {
        return testAnswerRepository.save(answer);
    }

    @Override
    public void deleteAnswer(Long id) {
        testAnswerRepository.deleteById(id);
    }

    @Override
    public TestAnswer updateAnswer(Long id, TestAnswer answer) {
        TestAnswer oldAnswer = (TestAnswer) testAnswerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found"));
        oldAnswer.setAnswer(answer.getAnswer());
        oldAnswer.setPoints(answer.getPoints());
        return testAnswerRepository.save(oldAnswer);
    }
}
