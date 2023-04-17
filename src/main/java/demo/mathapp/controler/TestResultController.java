package demo.mathapp.controler;


import demo.mathapp.DTO.TestResult.CreateTestResult;
import demo.mathapp.model.TestResult;
import demo.mathapp.service.TestAnswerService;
import demo.mathapp.service.TestResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TestResultController {

    private final TestResultService testResultService;
    private final TestAnswerService testAnswerService;


    @PostMapping("/result/create")
    public ResponseEntity<?> createTest(@RequestBody CreateTestResult testResult) {
        TestResult result = testResultService.createTestResult(testResult);
        testAnswerService.setAnswers(result.getAnswers(),result.getId());
        return ResponseEntity.ok().body(result);
    }
}
