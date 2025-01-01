package demo.mathapp.controller;

import demo.mathapp.SchoolType;
import demo.mathapp.service.impl.MaterialService;
import demo.mathapp.transferobject.MaterialDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/material")
public class MaterialController {

    private final MaterialService materialService;

    @GetMapping("/all")
    public ResponseEntity<List<MaterialDto>> getMaterialList() {
        return ResponseEntity.ok(materialService.getMaterialList());
    }

    @GetMapping("/{schoolType}")
    public ResponseEntity<List<MaterialDto>> getMaterialList(@PathVariable SchoolType schoolType) {
        return ResponseEntity.ok(materialService.getMaterialListBySchoolType(schoolType));
    }
}
