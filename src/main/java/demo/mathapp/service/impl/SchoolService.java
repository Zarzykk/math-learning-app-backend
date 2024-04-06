package demo.mathapp.service.impl;

import demo.mathapp.SchoolType;
import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.School;
import demo.mathapp.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public List<School> getSchoolsBySchoolType(SchoolType schoolType) {
        return schoolRepository.findSchoolsBySchoolType(schoolType);
    }

    public School createSchool(School school) {
        return schoolRepository.save(school);
    }

    public void deleteSchool(Long id) {
        schoolRepository.deleteById(id);
    }

    public School updateSchool(Long id, School school) {
        School oldSchool = schoolRepository.getById(id);
        oldSchool.setSchoolName(school.getSchoolName());
        oldSchool.setSchoolClasses(school.getSchoolClasses());
        return schoolRepository.save(oldSchool);
    }

    public School findSchoolById(Long schoolId) {
        return schoolRepository.findById(schoolId)
                .orElseThrow(() -> new ResourceNotFoundException("School with given id=" + schoolId + "  not found"));
    }
}
