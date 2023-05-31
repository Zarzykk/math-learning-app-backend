package demo.mathapp.service.impl;

import demo.mathapp.SchoolType;
import demo.mathapp.model.School;
import demo.mathapp.repository.SchoolRepository;
import demo.mathapp.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository schoolRepository;

    @Override
    public List<School> getSchoolsBySchoolType(SchoolType schoolType) {
        return schoolRepository.findSchoolsBySchoolType(schoolType);
    }

    @Override
    public School createSchool(School school) {
        return schoolRepository.save(school);
    }

    @Override
    public void deleteSchool(Long id) {
        schoolRepository.deleteById(id);
    }

    @Override
    public School updateSchool(Long id, School school) {
        School oldSchool = schoolRepository.getById(id);
        oldSchool.setSchoolName(school.getSchoolName());
        oldSchool.setSchoolClasses(school.getSchoolClasses());
        return schoolRepository.save(oldSchool);
    }
}
