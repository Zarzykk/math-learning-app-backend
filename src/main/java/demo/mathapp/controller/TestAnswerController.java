package demo.mathapp.controller;

import demo.mathapp.model.TestAnswer;
import demo.mathapp.service.TestAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test/answers")
public class TestAnswerController {
    private final TestAnswerService testAnswerService;

    @PutMapping("/update/{answerId}")
    public ResponseEntity<TestAnswer> updateAnswer(@RequestBody TestAnswer answer,
                                                   @PathVariable Long answerId) {
        return ResponseEntity.ok(testAnswerService.updateAnswer(answerId, answer));
    }

    @DeleteMapping("/delete/{answerId}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Long answerId) {
        testAnswerService.deleteAnswer(answerId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create")
    public ResponseEntity<TestAnswer> createAnswer(@RequestBody TestAnswer answer) {
        return ResponseEntity.ok(testAnswerService.createAnswer(answer));
    }
}
