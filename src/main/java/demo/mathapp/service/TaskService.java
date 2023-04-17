package demo.mathapp.service;

import demo.mathapp.DTO.TestTask.CreateTestTask;
import demo.mathapp.DTO.TestTask.GetTestTask;
import demo.mathapp.model.Task;
import demo.mathapp.model.Test;
import demo.mathapp.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;


    public Task createTestTask(CreateTestTask testTask) {
        return taskRepository.save(modelMapper.map(testTask, Task.class));
    }

    public List<GetTestTask> getTestTasks() {
        return taskRepository.findAll()
                .stream()
                .map(task -> modelMapper.map(task, GetTestTask.class))
                .collect(Collectors.toList());
    }


    public void setTaskTest(List<Task> tasks, long id) {
        Test test = new Test();
        test.setId(id);
        for (Task task : tasks) {
            task.setTest(test);
        }
        taskRepository.saveAll(tasks);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}
