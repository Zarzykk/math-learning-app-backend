package demo.mathapp.controller;

import demo.mathapp.model.HomeworkResult;
import demo.mathapp.service.impl.HomeworkResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/homework/results")
public class HomeworkResultController {
    private final HomeworkResultService homeworkResultService;

    @PutMapping("/update/{resultId}")
    public ResponseEntity<HomeworkResult> updateResult(@PathVariable Long resultId,
                                                       @RequestBody HomeworkResult result) {
        return ResponseEntity.ok(homeworkResultService.updateResult(resultId, result));
    }

    @DeleteMapping("/delete/{resultId}")
    public ResponseEntity<Void> deleteResult(@PathVariable Long resultId) {
        homeworkResultService.deleteResult(resultId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create")
    public ResponseEntity<HomeworkResult> createResult(@RequestBody HomeworkResult result) {
        return ResponseEntity.ok(homeworkResultService.createResult(result));
    }

}
