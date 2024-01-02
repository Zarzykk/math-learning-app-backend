package demo.mathapp.repository;

import demo.mathapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTasksByMaterialTopic_Name(String name);
    List<Task> findTasksByWork_Id(long id);
}
