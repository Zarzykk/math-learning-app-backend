package demo.mathapp.service;

import demo.mathapp.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {

    Task createTask(Task task);
    void deleteTask(Long id);
    Task updateTask(Long id,Task task);
    List<Task> findAllTasksByMaterial(String topic);
    List<Task> getListOfTasksForStudent(Long studentId);
}
