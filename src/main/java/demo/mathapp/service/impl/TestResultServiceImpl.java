package demo.mathapp.service.impl;

import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.TestResult;
import demo.mathapp.repository.TestResultRepository;
import demo.mathapp.service.TestResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestResultServiceImpl implements TestResultService {
    private final TestResultRepository testResultRepository;

    @Override
    public TestResult createResult(TestResult result) {
        return testResultRepository.save(result);
    }

    @Override
    public void deleteResult(Long id) {
        testResultRepository.deleteById(id);
    }

    @Override
    public TestResult updateResult(Long id, TestResult result) {
        TestResult oldResult = (TestResult) testResultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));
        oldResult.setPassed(result.isPassed());
        oldResult.setPoints(result.getPoints());
        oldResult.setWorkTimeResult(result.getWorkTimeResult());
        return testResultRepository.save(oldResult);
    }
}
