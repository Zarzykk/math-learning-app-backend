package demo.mathapp.controler;

import demo.mathapp.DTO.Student.CreateStudent;
import demo.mathapp.DTO.Student.GetStudentTestInfo;
import demo.mathapp.model.Student;
import demo.mathapp.model.User;
import demo.mathapp.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.status(201).body(
                studentService.createStudent(student)
        );
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Student> getStudentByEmail(@PathVariable String email) {
        return ResponseEntity.ok(studentService.getStudentByEmail(email));
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/id/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.getStudentById(studentId));
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId,
                                                 @RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(studentId, student));
    }
}
