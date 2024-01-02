package demo.mathapp.repository;

import demo.mathapp.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends UserRepository {

    Optional<Teacher> findTeacherByEmail(String email);

}
