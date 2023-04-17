package demo.mathapp.repository;

import demo.mathapp.model.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkRepository extends JpaRepository<Homework,Long> {
}
