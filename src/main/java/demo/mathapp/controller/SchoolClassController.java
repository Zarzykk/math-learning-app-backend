package demo.mathapp.controller;

import demo.mathapp.model.SchoolClass;
import demo.mathapp.service.SchoolClassService;
import demo.mathapp.transferobject.ClassTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/class")
public class SchoolClassController {
    @Qualifier("schoolClassService")
    private final SchoolClassService classService;

    @PostMapping("/create")
    public ResponseEntity<SchoolClass> createSchoolClass(@RequestBody SchoolClass schoolClass) {
        return ResponseEntity.ok(classService.createSchoolClass(schoolClass));
    }

    @GetMapping("get/school/{schoolName}")
    public ResponseEntity<List<SchoolClass>> getClassesBySchoolName(@PathVariable String schoolName) {
        return ResponseEntity.ok(classService.getSchoolClassesBySchoolName(schoolName));
    }

    @DeleteMapping("delete/{classId}")
    public ResponseEntity<?> deleteClass(@PathVariable Long classId) {
        classService.deleteSchoolClass(classId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{classId}")
    public ResponseEntity<SchoolClass> updateSchoolClass(@PathVariable Long classId,
                                                         @RequestBody SchoolClass schoolClass) {
        return ResponseEntity.ok(classService.updateSchoolClass(classId, schoolClass));
    }

    @GetMapping("/get/{teacherId}")
    public ResponseEntity<List<ClassTO>> getClassesByTeacher(@PathVariable Long teacherId){
        return ResponseEntity.ok(classService.getClassesByTeacher(teacherId));
    }
}
