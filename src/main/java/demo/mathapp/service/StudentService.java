package demo.mathapp.service;

import demo.mathapp.model.Student;
import demo.mathapp.transferobject.StudentTO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface StudentService {

    Student getStudentByEmail(String email);

    Student createStudent(Student student);

    void deleteStudent(Long id);

    Student updateStudent(Long id,Student student);

    Student getStudentById(Long id);

    List<StudentTO> getStudentsByClass(Long classId);

}
