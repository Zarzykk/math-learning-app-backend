package demo.mathapp.service.impl;

import demo.mathapp.ClassYear;
import demo.mathapp.PasswordEncoder;
import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.Student;
import demo.mathapp.model.User;
import demo.mathapp.repository.StudentRepository;
import demo.mathapp.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Student getStudentByEmail(String email) {
        return studentRepository.findStudentByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    @Override
    public Student createStudent(Student student) {
        student.setPassword(passwordEncoder.encodePassword(student.getPassword()));
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student updateStudent(Long id, Student newStudent) {
        Student oldStudent = getStudentById(id);
        oldStudent.setStudentClass(newStudent.getStudentClass());
        oldStudent.setEmail(newStudent.getEmail());
        oldStudent.setPassword(passwordEncoder.encodePassword(newStudent.getPassword()));
        return studentRepository.save(oldStudent);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findStudentById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }


}
