package demo.mathapp.repository;

import demo.mathapp.model.HomeworkResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkResultRepository extends JpaRepository<HomeworkResult, Long> {
}
