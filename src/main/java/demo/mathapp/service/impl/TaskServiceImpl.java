package demo.mathapp.service.impl;

import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.*;
import demo.mathapp.repository.TaskRepository;
import demo.mathapp.service.TaskService;
import demo.mathapp.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TestService testService;

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Task oldTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        oldTask.setPoints(task.getPoints());
        oldTask.setText(task.getText());
        oldTask.setMaterialTopic(task.getMaterialTopic());
        return taskRepository.save(oldTask);
    }

    @Override
    public List<Task> findAllTasksByMaterial(String topic) {
        return taskRepository.findTasksByMaterialTopic_Name(topic);
    }

    @Override
    public List<Task> getListOfTasksForStudent(Long studentId) {
        List<Test> tests = testService.findAllTestsPassedOrFailedForStudent(studentId, false);
        for (Test test : tests) {

        }
        return null;
    }

}
