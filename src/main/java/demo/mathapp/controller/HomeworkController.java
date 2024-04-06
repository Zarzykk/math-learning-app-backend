package demo.mathapp.controller;

import demo.mathapp.model.Homework;
import demo.mathapp.service.impl.HomeworkService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/homeworks")
public class HomeworkController {
    private final HomeworkService homeworkService;

    @PostMapping("/create")
    public ResponseEntity<Homework> createHomework(@RequestBody Homework homework) {
        return ResponseEntity.ok(homeworkService.createHomework(homework));
    }

    @DeleteMapping("/delete/{homeworkId}")
    public ResponseEntity<Void> deleteHomework(@PathVariable Long homeworkId) {
        homeworkService.deleteHomework(homeworkId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{homeworkId}")
    public ResponseEntity<Homework> updateHomeWork(@RequestBody Homework homework,
                                                   @PathVariable Long homeworkId) {
        return ResponseEntity.ok(homeworkService.updateHomework(homeworkId, homework));
    }
}
