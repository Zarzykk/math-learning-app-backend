package demo.mathapp.repository;

import demo.mathapp.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends UserRepository {

    Optional<Student> findStudentByEmail(String email);
    Optional<Student> findStudentById(Long id);
}
