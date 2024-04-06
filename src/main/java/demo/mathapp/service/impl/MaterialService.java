package demo.mathapp.service.impl;

import demo.mathapp.SchoolType;
import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.Material;
import demo.mathapp.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialService {
    private final MaterialRepository materialRepository;

    public List<Material> findMaterialsBySchoolType(SchoolType schoolType) {
        return materialRepository.findMaterialsBySchoolType(schoolType);
    }

    public Material createMaterial(Material material) {
        return materialRepository.save(material);
    }

    public Material updateMaterial(Long id, Material material) {
        material.setId(id);
        return materialRepository.save(material);
    }

    public void deleteMaterial(Long id) {
        materialRepository.deleteById(id);
    }

    public Material findMaterialBySchoolTypeAndYear(SchoolType schoolType, int classYear) {
        return materialRepository.findMaterialBySchoolTypeAndClassYear(schoolType, classYear);
    }

    public Material findMaterialById(Long id) {
        return materialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Material not found"));
    }
}
