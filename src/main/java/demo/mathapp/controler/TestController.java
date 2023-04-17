package demo.mathapp.controler;

import demo.mathapp.DTO.Test.CreateTest;
import demo.mathapp.DTO.Test.GetTest;
import demo.mathapp.model.Test;
import demo.mathapp.service.TaskService;
import demo.mathapp.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TestController {

    private final TestService testService;
    private final TaskService taskService;

    @PostMapping("/test/create")
    public ResponseEntity<Test> createTest(@RequestBody CreateTest createTest) {
        Test test = testService.createTest(createTest);
        taskService.setTaskTest(test.getTasks(), test.getId());
        return ResponseEntity.status(201).body(test);
    }

    @GetMapping("/test/{id}")
    public GetTest getTest(@PathVariable Long id) {
        return testService.getClassTest(id);
    }

    @DeleteMapping("/test/{id}")
    public ResponseEntity<?> deleteTest(@PathVariable Long id) {
        testService.deleteTest(id);
        return ResponseEntity.ok().build();
    }

}
