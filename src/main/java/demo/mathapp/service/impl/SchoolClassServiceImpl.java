package demo.mathapp.service.impl;

import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.Material;
import demo.mathapp.model.SchoolClass;
import demo.mathapp.model.Student;
import demo.mathapp.repository.SchoolClassRepository;
import demo.mathapp.service.MaterialService;
import demo.mathapp.service.SchoolClassService;
import demo.mathapp.service.StudentService;
import demo.mathapp.transferobject.ClassTO;
import demo.mathapp.transferobject.StudentTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolClassServiceImpl implements SchoolClassService {
    private final SchoolClassRepository classRepository;
    private final MaterialService materialService;

    @Override
    public List<SchoolClass> getSchoolClassesBySchoolName(String schoolName) {
        return classRepository.findSchoolClassBySchool_SchoolName(schoolName);
    }

    @Override
    public SchoolClass createSchoolClass(SchoolClass schoolClass) {
        Material material = materialService.findMaterialBySchoolTypeAndYear(
                schoolClass.getSchool().getSchoolType(),
                schoolClass.getClassYear()
        );
        schoolClass.setMaterial(material);
        return classRepository.save(schoolClass);
    }

    @Override
    public SchoolClass updateSchoolClass(Long id, SchoolClass schoolClass) {
        SchoolClass oldClass = classRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found"));
        oldClass.setStudents(schoolClass.getStudents());
        return classRepository.save(oldClass);
    }

    @Override
    public void deleteSchoolClass(Long classId) {
        classRepository.deleteById(classId);
    }

    @Override
    public List<ClassTO> getClassesByTeacher(Long teacherId) {
        List<ClassTO> classTOList = new ArrayList<>();
        List<SchoolClass> allByTeacherId = classRepository.findAllByTeacher_Id(teacherId);
        for (SchoolClass schoolClass : allByTeacherId) {
            ClassTO to = new ClassTO();
            to.setId(schoolClass.getId());
            to.setClassYearAndIndex(schoolClass.getClassYear().toString() + schoolClass.getClassIndex());
            classTOList.add(to);
        }
        return classTOList;
    }

}
