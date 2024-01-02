package demo.mathapp.controller;

import demo.mathapp.ClassYear;
import demo.mathapp.SchoolType;
import demo.mathapp.model.Material;
import demo.mathapp.service.MaterialService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/materials")
public class MaterialController {
    private final MaterialService materialService;

    @PostMapping("/create")
    public ResponseEntity<Material> createMaterial(@RequestBody Material material) {
        return ResponseEntity.ok(materialService.createMaterial(material));
    }

    @GetMapping("/school/{schoolType}")
    public ResponseEntity<List<Material>> getMaterialsBySchoolType(@PathVariable SchoolType schoolType) {
        return ResponseEntity.ok(materialService.findMaterialsBySchoolType(schoolType));
    }

    @GetMapping("/school/{schoolType}/class/{year}")
    public ResponseEntity<Material> getMaterialBySchoolTypeAndYear(@PathVariable SchoolType schoolType,
                                                                   @PathVariable ClassYear year) {
        return ResponseEntity.ok(materialService.findMaterialBySchoolTypeAndYear(schoolType, year));
    }

    @DeleteMapping("/delete/{materialId}")
    public ResponseEntity<?> deleteMaterial(@PathVariable Long materialId) {
        materialService.deleteMaterial(materialId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{materialId}")
    public ResponseEntity<Material> updateMaterial(@PathVariable Long materialId,
                                                   @RequestBody Material material) {
        return ResponseEntity.ok(materialService.updateMaterial(materialId, material));
    }
}
