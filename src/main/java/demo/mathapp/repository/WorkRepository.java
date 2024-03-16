package demo.mathapp.repository;

import demo.mathapp.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface WorkRepository extends JpaRepository<Work, Long> {

    @Query(nativeQuery = true,
            value = "select * from work where work_type = ?1")
    List<Work> findWorksByWorkType(String workType);

    List<Work> findAllBySchoolClass_Teacher_Id(Long teacherId);
}
