package demo.mathapp.service;

import demo.mathapp.ClassYear;
import demo.mathapp.SchoolType;
import demo.mathapp.model.Material;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MaterialService {
    List<Material> findMaterialsBySchoolType(SchoolType schoolType);

    Material createMaterial(Material material);

    Material updateMaterial(Long id,Material material);

    void deleteMaterial(Long id);

    Material findMaterialBySchoolTypeAndYear(SchoolType schoolType,ClassYear classYear);
}
