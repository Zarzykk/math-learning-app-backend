package demo.mathapp.service.impl;

import demo.mathapp.config.SecurityConfig;
import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.SchoolClass;
import demo.mathapp.model.Student;
import demo.mathapp.repository.SchoolClassRepository;
import demo.mathapp.repository.StudentRepository;
import demo.mathapp.transferobject.student.StudentBodyTO;
import demo.mathapp.transferobject.student.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<StudentDto> getStudentsByClass(Long classId) {
        List<StudentDto> studentDtos = new ArrayList<>();
        SchoolClass schoolClass = classRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found class by id=" + classId));
        for (Student student : schoolClass.getStudents()) {
            StudentDto to = new StudentDto();
            to.setId(student.getId());
            to.setFirstName(student.getFirstName());
            to.setLastName(student.getLastName());
            studentDtos.add(to);
        }
        return studentDtos;
    }

    public StudentBodyTO getStudentDetails(Long studentId) {
        Student student = getStudentById(studentId);
        return null;
    }


    public List<StudentDto> getStudentsByTeacherId(Long teacherId) {
        List<SchoolClass> allTeacherClasses = classRepository.findAllByTeacher_Id(teacherId);
        List<StudentDto> studentDtos = new ArrayList<>();
        allTeacherClasses.forEach(schoolClass -> {
                    studentDtos.addAll(schoolClass.getStudents().stream()
                            .map(student -> StudentDto.builder()
                                    .id(student.getId())
                                    .schoolClassId(schoolClass.getId())
                                    .classIndex(schoolClass.getClassName())
                                    .firstName(student.getFirstName())
                                    .lastName(student.getLastName())
                                    .build())
                            .toList());
                });
        return studentDtos;
    }
}
