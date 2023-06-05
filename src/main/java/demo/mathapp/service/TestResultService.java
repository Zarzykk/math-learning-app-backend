package demo.mathapp.service;

import demo.mathapp.model.TestResult;
import org.springframework.stereotype.Service;

@Service
public interface TestResultService {

    TestResult createResult(TestResult result);

    void deleteResult(Long id);

    TestResult updateResult(Long id,TestResult result);
}
