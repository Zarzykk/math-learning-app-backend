package demo.mathapp.controller;

import demo.mathapp.SchoolType;
import demo.mathapp.model.School;
import demo.mathapp.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schools")
public class SchoolController {

    private final SchoolService schoolService;

    @PostMapping("/create")
    public ResponseEntity<School> createSchool(@RequestBody School school){
        return ResponseEntity.ok(schoolService.createSchool(school));
    }

    @GetMapping("/get/{type}")
    public ResponseEntity<List<School>> getSchoolsByType(@PathVariable SchoolType type){
        return ResponseEntity.ok(schoolService.getSchoolsBySchoolType(type));
    }

    @DeleteMapping("/delete/{schoolId}")
    public ResponseEntity<?> deleteSchool(@PathVariable Long schoolId){
        schoolService.deleteSchool(schoolId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{schoolId}")
    public ResponseEntity<School> updateSchool(@PathVariable Long schoolId,
                                               @RequestBody School school){
        return ResponseEntity.ok(schoolService.updateSchool(schoolId, school));
    }
}
