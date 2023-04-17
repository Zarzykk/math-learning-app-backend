package demo.mathapp.controler;

import demo.mathapp.DTO.TestTask.CreateTestTask;
import demo.mathapp.DTO.TestTask.GetTestTask;
import demo.mathapp.model.Task;
import demo.mathapp.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/test/task")
    public ResponseEntity<Task> createTestTask(@RequestBody CreateTestTask task){
        return ResponseEntity.status(201).body(
                taskService.createTestTask(task)
        );
    }

    @GetMapping("/test")
    public List<GetTestTask> getTestTasks(){
        return taskService.getTestTasks();
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }
}
