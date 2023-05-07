package demo.mathapp.service;

import demo.mathapp.model.Teacher;
import demo.mathapp.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {

    Teacher createTeacher(Teacher teacher);

    void deleteTeacher(Long id);

    Teacher updateTeacher(Long id,Teacher teacher);

    Teacher getTeacherById(Long id);

    Teacher getTeacherByEmail(String email);
}
