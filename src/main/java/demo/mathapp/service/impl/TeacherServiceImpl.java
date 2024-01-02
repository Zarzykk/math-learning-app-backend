package demo.mathapp.service.impl;

import demo.mathapp.PasswordEncoder;
import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.Teacher;
import demo.mathapp.repository.TeacherRepository;
import demo.mathapp.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Teacher createTeacher(Teacher teacher) {
        teacher.setPassword(passwordEncoder.encodePassword(teacher.getPassword()));
        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public Teacher updateTeacher(Long id, Teacher teacher) {
        Teacher oldTeacher = getTeacherById(id);
        oldTeacher.setPassword(passwordEncoder.encodePassword(teacher.getPassword()));
        return null;
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return (Teacher) teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
    }

    @Override
    public Teacher getTeacherByEmail(String email) {
        return teacherRepository.findTeacherByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
    }


}
