package demo.mathapp.controller;

import demo.mathapp.model.Student;
import demo.mathapp.service.impl.StudentService;
import demo.mathapp.transferobject.student.StudentBodyTO;
import demo.mathapp.transferobject.student.StudentTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    //TODO usun nazwy metod 'create, get itd.' ze sciezek
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.status(201).body(
                studentService.createStudent(student)
        );
    }

    @GetMapping("/get/email/{email}")
    public ResponseEntity<Student> getStudentByEmail(@PathVariable String email) {
        return ResponseEntity.ok(studentService.getStudentByEmail(email));
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
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

    @GetMapping("/get/{classId}")
    public ResponseEntity<List<StudentTO>> getStudentsByClass(@PathVariable Long classId){
        return ResponseEntity.ok(studentService.getStudentsByClass(classId));
    }

    @GetMapping("/get/{studentId}/details")
    public ResponseEntity<StudentBodyTO> getStudentDetails(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.getStudentDetails(studentId));
    }
}
