package demo.mathapp.service.impl;

import demo.mathapp.config.SecurityConfig;
import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.SchoolClass;
import demo.mathapp.model.Teacher;
import demo.mathapp.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final SecurityConfig passwordEncoder;
    private final SchoolClassService schoolClassService;

    public Teacher createTeacher(Teacher teacher) {
        teacher.setPassword(passwordEncoder.encodePassword(teacher.getPassword()));
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long oldTeacherId, Long substituteTeacherId) {
        setSubstituteTeacher(oldTeacherId, substituteTeacherId);
        teacherRepository.deleteById(oldTeacherId);
    }

    public Teacher updateTeacher(Long id, Teacher teacher) {
        teacher.setId(id);
        return teacherRepository.save(teacher);
    }

    public Teacher getTeacherById(Long id) {
        return (Teacher) teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
    }

    public Teacher getTeacherByEmail(String email) {
        return teacherRepository.findTeacherByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
    }

    public void setSubstituteTeacher(Long oldTeacherId, Long newTeacherId) {
        Teacher teacher = getTeacherById(newTeacherId);
        List<SchoolClass> classesByTeacher = schoolClassService.getClassesByTeacher(oldTeacherId);
        classesByTeacher.forEach(schoolClass -> schoolClass.setTeacher(teacher));
        teacher.getClasses().addAll(classesByTeacher);
        teacherRepository.save(teacher);
    }

}
