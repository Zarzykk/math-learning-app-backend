package demo.mathapp.controller;

import demo.mathapp.model.HomeworkAnswer;
import demo.mathapp.service.HomeworkAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/homework/answers")
public class HomeworkAnswerController {
    private final HomeworkAnswerService homeworkAnswerService;

    @PostMapping("/create")
    public ResponseEntity<HomeworkAnswer> createAnswer(@RequestBody HomeworkAnswer answer) {
        return ResponseEntity.ok(homeworkAnswerService.createAnswer(answer));
    }

    @PutMapping("/update/{answerId}")
    public ResponseEntity<HomeworkAnswer> updateAnswer(@PathVariable Long answerId,
                                                       @RequestBody HomeworkAnswer homeworkAnswer) {
        return ResponseEntity.ok(homeworkAnswerService.updateAnswer(answerId, homeworkAnswer));
    }

    @DeleteMapping("/delete/{answerId}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long answerId) {
        homeworkAnswerService.deleteAnswer(answerId);
        return ResponseEntity.noContent().build();
    }
}
