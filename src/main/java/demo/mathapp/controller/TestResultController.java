package demo.mathapp.controller;

import demo.mathapp.model.TestResult;
import demo.mathapp.service.TestResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test/results")
public class TestResultController {
    private final TestResultService testResultService;

    @PutMapping("/update/{resultId}")
    public ResponseEntity<TestResult> updateResult(@PathVariable Long resultId,
                                                   @RequestBody TestResult testResult) {
        return ResponseEntity.ok(testResultService.updateResult(resultId, testResult));
    }

    @DeleteMapping("/delete/{resultId}")
    public ResponseEntity<?> deleteResult(@PathVariable Long resultId) {
        testResultService.deleteResult(resultId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create")
    public ResponseEntity<TestResult> createResult(@RequestBody TestResult result) {
        return ResponseEntity.ok(testResultService.createResult(result));
    }

}
