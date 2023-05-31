package demo.mathapp.service.impl;

import demo.mathapp.repository.TaskRepository;
import demo.mathapp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
}
