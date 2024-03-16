package demo.mathapp.controller;

import demo.mathapp.model.Test;
import demo.mathapp.service.TestService;
import demo.mathapp.transferobject.test.TestBodyTO;
import demo.mathapp.transferobject.test.TestHeaderTO;
import demo.mathapp.transferobject.test.TestTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tests")
public class TestController {
    private final TestService testService;

    @PostMapping("/create")
    public ResponseEntity<Test> createTest(@RequestBody Test test) {
        return ResponseEntity.ok(testService.createTest(test));
    }

    @DeleteMapping("/delete/{testId}")
    public ResponseEntity<?> deleteTest(@PathVariable Long testId) {
        testService.deleteTest(testId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{testId}")
    public ResponseEntity<Test> updateTest(@PathVariable Long testId,
                                           @RequestBody Test test) {
        return ResponseEntity.ok(testService.updateTest(testId, test));
    }

    @GetMapping("/get/teacher/{teacherId}")
    public ResponseEntity<List<TestHeaderTO>> getTeacherTests(@PathVariable Long teacherId,
                                                              @RequestParam Optional<Long> classId) {
        return ResponseEntity.ok(testService.findTeacherTests(teacherId, classId));
    }

    @GetMapping("/get/{testId}")
    public ResponseEntity<TestTO> getTest(@PathVariable Long testId) {
        return ResponseEntity.ok(testService.getTest(testId));
    }

    @GetMapping("/get/{testId}/details")
    public ResponseEntity<TestBodyTO> getTestDetails(@PathVariable Long testId) {
        return ResponseEntity.ok(testService.getTestDetails(testId));
    }
}
