package demo.mathapp.controler;

import demo.mathapp.DTO.SchoolClass.CreateSchoolClass;
import demo.mathapp.model.SchoolClass;
import demo.mathapp.service.SchoolClassService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/class")
public class SchoolClassController {
    private final SchoolClassService schoolClassService;

    @PostMapping("/create")
    public ResponseEntity<SchoolClass> createSchoolClass(@RequestBody CreateSchoolClass createSchoolClass){
        SchoolClass schoolClass = schoolClassService.createSchoolClass(createSchoolClass);
    return ResponseEntity.ok(schoolClass);
    }

}
