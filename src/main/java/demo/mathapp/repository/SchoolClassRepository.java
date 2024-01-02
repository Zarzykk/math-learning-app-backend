package demo.mathapp.repository;

import demo.mathapp.model.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {

    List<SchoolClass> findSchoolClassBySchool_SchoolName(String schoolName);

    List<SchoolClass> findAllByTeacher_Id(Long teacherId);
}
