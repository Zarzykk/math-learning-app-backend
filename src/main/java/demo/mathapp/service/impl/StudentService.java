package demo.mathapp.service.impl;

import demo.mathapp.config.SecurityConfig;
import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.SchoolClass;
import demo.mathapp.model.Student;
import demo.mathapp.repository.SchoolClassRepository;
import demo.mathapp.repository.StudentRepository;
import demo.mathapp.transferobject.student.StudentBodyTO;
import demo.mathapp.transferobject.student.StudentTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final SecurityConfig passwordEncoder;
    private final SchoolClassRepository classRepository;

    public Student getStudentByEmail(String email) {
        return studentRepository.findStudentByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    public Student createStudent(Student student) {
        student.setPassword(passwordEncoder.encodePassword(student.getPassword()));
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Long id, Student student) {
        student.setId(id);
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return (Student) studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

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
