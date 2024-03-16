package demo.mathapp.repository;

import demo.mathapp.SchoolType;
import demo.mathapp.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    Material findMaterialBySchoolTypeAndClassYear(SchoolType schoolType,int classYear);
    List<Material> findMaterialsBySchoolType(SchoolType schoolType);
}
