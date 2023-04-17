package demo.mathapp.repository;

import demo.mathapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, Long> {


}
