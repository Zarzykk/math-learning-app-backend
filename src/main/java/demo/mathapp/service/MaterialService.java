package demo.mathapp.service;

import demo.mathapp.ClassYear;
import demo.mathapp.DTO.Material.CreateMaterial;
import demo.mathapp.SchoolType;
import demo.mathapp.model.Material;
import demo.mathapp.repository.MaterialRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MaterialService {
    private final MaterialRepository materialRepository;
    private final ModelMapper modelMapper;

    public Material createMaterial(CreateMaterial material){
        return materialRepository.save(modelMapper.map(material,Material.class));
    }

    public List<Material> getSchoolMaterials(SchoolType schoolType){
        return materialRepository.findMaterialsBySchoolType(schoolType);
    }


    public Material getMaterialByYear(SchoolType schoolType, ClassYear classYear) {
        Material material = new Material();
        material.setId(materialRepository.findMaterialBySchoolTypeAndClassYear(schoolType,classYear).getId());
        return material;
    }
}
