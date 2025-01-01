package demo.mathapp.service.impl;

import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.Material;
import demo.mathapp.model.SchoolClass;
import demo.mathapp.repository.SchoolClassRepository;
import demo.mathapp.transferobject.SchoolClassDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolClassService {
    private final SchoolClassRepository classRepository;
    private final MaterialService materialService;

    public List<SchoolClass> getSchoolClassesBySchoolName(String schoolName) {
        return classRepository.findSchoolClassBySchool_SchoolName(schoolName);
    }

    public List<SchoolClass> getAllClasses() {
        return classRepository.findAll();
    }

    public SchoolClass createSchoolClass(SchoolClass schoolClass) {
//        Material material = materialService.findMaterialBySchoolTypeAndYear(
//                schoolClass.getSchool().getSchoolType(),
//                schoolClass.getClassYear()
//        );
//        schoolClass.setMaterial(material);
        return classRepository.save(schoolClass);
    }


    public SchoolClass updateSchoolClass(Long id, SchoolClass schoolClass) {
        SchoolClass oldClass = classRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found"));
        oldClass.setStudents(schoolClass.getStudents());
        return classRepository.save(oldClass);
    }


    public void deleteSchoolClass(Long classId) {
        classRepository.deleteById(classId);
    }


    public List<SchoolClassDTO> getClassesByTeacher(Long teacherId) {
        return classRepository.findAllByTeacher_Id(teacherId).stream()
                .map(schoolClass -> SchoolClassDTO.builder()
                        .id(schoolClass.getId())
                        .classIndex(schoolClass.getClassIndex())
                        .classYear(schoolClass.getClassYear())
                        .teacherId(schoolClass.getTeacher().getId())
                        .build())
                .collect(Collectors.toList());
    }

}
