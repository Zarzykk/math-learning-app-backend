package demo.mathapp.service.impl;

import demo.mathapp.ClassYear;
import demo.mathapp.SchoolType;
import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.Material;
import demo.mathapp.repository.MaterialRepository;
import demo.mathapp.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;

    @Override
    public List<Material> findMaterialsBySchoolType(SchoolType schoolType) {
        return materialRepository.findMaterialsBySchoolType(schoolType);
    }

    @Override
    public Material createMaterial(Material material) {
        return materialRepository.save(material);
    }

    @Override
    public Material updateMaterial(Long id, Material material) {
        Material oldMaterial = materialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Material not found"));
        oldMaterial.setClassList(material.getClassList());
        return materialRepository.save(oldMaterial);
    }

    @Override
    public void deleteMaterial(Long id) {
        materialRepository.deleteById(id);
    }

    @Override
    public Material findMaterialBySchoolTypeAndYear(SchoolType schoolType, ClassYear classYear) {
        return materialRepository.findMaterialBySchoolTypeAndClassYear(schoolType, classYear);
    }
}
