package demo.mathapp.repository;

import demo.mathapp.model.WorkAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface WorkAnswerRepository extends JpaRepository<WorkAnswer,Long> {


}
