package demo.mathapp.controller;

import demo.mathapp.model.Teacher;
import demo.mathapp.service.impl.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping("/create")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher){
        return ResponseEntity.ok(teacherService.createTeacher(teacher));
    }

    @GetMapping("get/email/{email}")
    public ResponseEntity<Teacher> getTeacherByEmail(@PathVariable String email) {
        return ResponseEntity.ok(teacherService.getTeacherByEmail(email));
    }

    @DeleteMapping("/delete/{teacherId}/{substituteTeacherId}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long teacherId,
                                           @PathVariable Long substituteTeacherId) {
        teacherService.deleteTeacher(teacherId, substituteTeacherId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("get/id/{teacherId}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long teacherId) {
        return ResponseEntity.ok(teacherService.getTeacherById(teacherId));
    }

    @PutMapping("/update/{teacherId}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long teacherId,
                                                 @RequestBody Teacher teacher) {
        return ResponseEntity.ok(teacherService.updateTeacher(teacherId, teacher));
    }
}
