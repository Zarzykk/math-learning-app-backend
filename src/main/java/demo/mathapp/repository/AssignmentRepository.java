package demo.mathapp.repository;

import demo.mathapp.model.Assignment;
import demo.mathapp.model.AssignmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    List<Assignment> findAllBySchoolClass_Id(Long id);

    List<Assignment> findAllBySchoolClass_Teacher_IdAndType(Long teacherId, AssignmentType type);
}
