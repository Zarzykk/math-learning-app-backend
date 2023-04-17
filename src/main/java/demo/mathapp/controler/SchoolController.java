package demo.mathapp.controler;

import demo.mathapp.DTO.School.CreateSchool;
import demo.mathapp.DTO.School.GetSchoolName;
import demo.mathapp.SchoolType;
import demo.mathapp.model.School;
import demo.mathapp.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/school")
public class SchoolController {

    private final SchoolService schoolService;

    @PostMapping("/create")
    public ResponseEntity<School> createSchool(@RequestBody CreateSchool createSchool){
        return ResponseEntity.ok(schoolService.createSchool(createSchool));
    }

    @GetMapping("/{type}")
    public ResponseEntity<List<GetSchoolName>> getSchoolByType(@PathVariable SchoolType type){
        return ResponseEntity.ok(schoolService.getSchoolsByType(type));
    }
}
