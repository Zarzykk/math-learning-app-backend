package demo.mathapp.controler;

import demo.mathapp.DTO.Material.CreateMaterial;
import demo.mathapp.SchoolType;
import demo.mathapp.model.Material;
import demo.mathapp.service.MaterialService;
import demo.mathapp.service.MaterialTopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/material")
public class MaterialController {
    private final MaterialService materialService;
    private final MaterialTopicService materialTopicService;

    @PostMapping("/create")
    public ResponseEntity<Material> createMaterial(@RequestBody CreateMaterial createMaterial){
        Material material = materialService.createMaterial(createMaterial);
        materialTopicService.setMaterialTopics(material.getMaterialTopics(),material.getId());
        return ResponseEntity.ok(material);
    }

    @GetMapping
    public ResponseEntity<List<Material>> getSchoolMaterials(@PathVariable SchoolType schoolType){
        List<Material> materialList = materialService.getSchoolMaterials(schoolType);
        return ResponseEntity.ok(materialList);
    }
}
