package demo.mathapp.service;

import demo.mathapp.model.SchoolClass;
import demo.mathapp.transferobject.ClassTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SchoolClassService {
    List<SchoolClass> getSchoolClassesBySchoolName(String schoolName);

    SchoolClass createSchoolClass(SchoolClass schoolClass);

    SchoolClass updateSchoolClass(Long id,SchoolClass schoolClass);

    void deleteSchoolClass(Long classId);

    List<ClassTO> getClassesByTeacher(Long teacherId);
}
