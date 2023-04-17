package demo.mathapp.controler;

import demo.mathapp.DTO.Student.CreateStudent;
import demo.mathapp.DTO.Student.GetStudentTestInfo;
import demo.mathapp.model.Student;
import demo.mathapp.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody CreateStudent student){
        return ResponseEntity.status(201).body(
                studentService.createStudent(student)
        );
    }

    @GetMapping
    public List<GetStudentTestInfo> getAllStudents(){
        return studentService.getAllStudents();
    }

    @DeleteMapping("/student/{studentId}")
    public void deleteStudent(@PathVariable Long studentId){
        studentService.deleteStudent(studentId);
    }
}
