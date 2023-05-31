package demo.mathapp.repository;

import demo.mathapp.model.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkRepository extends WorkRepository {
}
