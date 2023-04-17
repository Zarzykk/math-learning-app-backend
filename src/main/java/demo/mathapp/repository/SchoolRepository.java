package demo.mathapp.repository;

import demo.mathapp.SchoolType;
import demo.mathapp.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School,Long> {

    List<School> findSchoolsBySchoolType(SchoolType type);
}
