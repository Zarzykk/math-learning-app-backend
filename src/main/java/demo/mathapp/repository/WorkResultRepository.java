package demo.mathapp.repository;

import demo.mathapp.model.WorkResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface WorkResultRepository extends JpaRepository<WorkResult,Long> {

}
