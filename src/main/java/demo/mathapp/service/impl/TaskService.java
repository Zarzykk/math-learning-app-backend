package demo.mathapp.service.impl;

import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.*;
import demo.mathapp.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TestService testService;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task updateTask(Long id, Task task) {
        Task oldTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        oldTask.setPoints(task.getPoints());
        oldTask.setText(task.getText());
        oldTask.setMaterialTopic(task.getMaterialTopic());
        return taskRepository.save(oldTask);
    }

    public List<Task> findAllTasksByMaterial(String topic) {
        return taskRepository.findTasksByMaterialTopic_Name(topic);
    }

    public List<Task> getListOfTasksForStudent(Long studentId) {
        List<Test> tests = testService.findAllTestsPassedOrFailedForStudent(studentId, false);
        for (Test test : tests) {

        }
        return null;
    }

}
