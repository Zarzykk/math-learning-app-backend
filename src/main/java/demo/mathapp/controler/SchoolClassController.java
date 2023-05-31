package demo.mathapp.controler;

import demo.mathapp.DTO.SchoolClass.CreateSchoolClass;
import demo.mathapp.model.SchoolClass;
import demo.mathapp.service.SchoolClassService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/class")
public class SchoolClassController {
    private final SchoolClassService classService;

    @PostMapping("/create")
    public ResponseEntity<SchoolClass> createSchoolClass(@RequestBody SchoolClass schoolClass) {
        return ResponseEntity.ok(classService.createSchoolClass(schoolClass));
    }

    @GetMapping("/school/{schoolName}")
    public ResponseEntity<List<SchoolClass>> getClassesBySchoolName(@PathVariable String schoolName) {
        return ResponseEntity.ok(classService.getSchoolClassesBySchoolName(schoolName));
    }

    @DeleteMapping("/{classId}")
    public ResponseEntity<?> deleteClass(@PathVariable Long classId) {
        classService.deleteSchoolClass(classId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{classId}")
    public ResponseEntity<SchoolClass> updateSchoolClass(@PathVariable Long classId,
                                                         @RequestBody SchoolClass schoolClass) {
        return ResponseEntity.ok(classService.updateSchoolClass(classId, schoolClass));
    }

}
