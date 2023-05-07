package demo.mathapp.service;

import demo.mathapp.ClassYear;
import demo.mathapp.DTO.Student.CreateStudent;
import demo.mathapp.model.Student;
import demo.mathapp.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface StudentService {

    Student getStudentByEmail(String email);

    Student createStudent(Student student);

    void deleteStudent(Long id);

    Student updateStudent(Long id,Student student);

    Student getStudentById(Long id);

}
