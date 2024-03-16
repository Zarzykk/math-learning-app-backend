package demo.mathapp.service.impl;

import demo.mathapp.PasswordEncoder;
import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.SchoolClass;
import demo.mathapp.model.Student;
import demo.mathapp.repository.SchoolClassRepository;
import demo.mathapp.repository.StudentRepository;
import demo.mathapp.service.StudentService;
import demo.mathapp.transferobject.student.StudentBodyTO;
import demo.mathapp.transferobject.student.StudentTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final SchoolClassRepository classRepository;

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
        return (Student) studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    @Override
    public List<StudentTO> getStudentsByClass(Long classId) {
        List<StudentTO> studentTOs = new ArrayList<>();
        SchoolClass schoolClass = classRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found class by id=" + classId));
        for (Student student : schoolClass.getStudents()) {
         StudentTO to = new StudentTO();
         to.setId(student.getId());
         to.setFirstName(student.getFirstName());
         to.setLastName(student.getLastName());
         studentTOs.add(to);
        }
        return studentTOs;
    }

    @Override
    public StudentBodyTO getStudentDetails(Long studentId) {
        Student student = getStudentById(studentId);
        student.getWorkResults();
//        StudentBodyTO to = StudentBodyTO
//                .builder()
//                .
//                .build();
        return null;
    }


}
