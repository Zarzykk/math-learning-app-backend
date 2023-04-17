package demo.mathapp.service;

import demo.mathapp.DTO.Student.CreateStudent;
import demo.mathapp.DTO.Student.GetStudentTestInfo;
import demo.mathapp.model.Student;
import demo.mathapp.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {
    private final UserService userService;

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public Student createStudent(CreateStudent createStudent) {
        Student student = modelMapper.map(createStudent, Student.class);
        student.setUser(userService.createUser(createStudent.getUser()));
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<GetStudentTestInfo> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(student -> modelMapper.map(student,GetStudentTestInfo.class))
                .collect(Collectors.toList());
    }
}
