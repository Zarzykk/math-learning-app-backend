package demo.mathapp.controler;

import demo.mathapp.DTO.Teacher.CreateTeacher;
import demo.mathapp.model.Teacher;
import demo.mathapp.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping("/create")
    public ResponseEntity<Teacher> createTeacher(@RequestBody CreateTeacher createTeacher){
        return ResponseEntity.ok(teacherService.createTeacher(createTeacher));
    }
}
