package demo.mathapp.repository;

import demo.mathapp.model.AssignmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentResultRepository extends JpaRepository<AssignmentResult, Long> {

    List<AssignmentResult> findAllByStudent_Id(Long studentId);

    List<AssignmentResult> findAllByStudent_IdAndAssignment_Id(Long studentId, Long assignmentId);

}
