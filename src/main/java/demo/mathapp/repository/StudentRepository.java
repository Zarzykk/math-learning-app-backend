package demo.mathapp.repository;

import demo.mathapp.ClassYear;
import demo.mathapp.model.Student;
import demo.mathapp.model.User;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends UserRepository {

    Optional<Student> findStudentByEmail(String email);
    Optional<Student> findStudentById(Long id);
}
