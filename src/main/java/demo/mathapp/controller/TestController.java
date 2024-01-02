package demo.mathapp.controller;

import demo.mathapp.model.Test;
import demo.mathapp.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tests")
public class TestController {
    private final TestService testService;

    @PostMapping("/create")
    public ResponseEntity<Test> createTest(@RequestBody Test test){
        return ResponseEntity.ok(testService.createTest(test));
    }

    @DeleteMapping("/delete/{testId}")
    public ResponseEntity<?> deleteTest(@PathVariable Long testId){
        testService.deleteTest(testId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{testId}")
    public ResponseEntity<Test> updateTest(@PathVariable Long testId,
                                           @RequestBody Test test){
        return ResponseEntity.ok(testService.updateTest(testId, test));
    }
}
