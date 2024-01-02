package demo.mathapp.service;

import demo.mathapp.SchoolType;
import demo.mathapp.model.School;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SchoolService{
    List<School> getSchoolsBySchoolType(SchoolType schoolType);

    School createSchool(School school);

    void deleteSchool(Long id);

    School updateSchool(Long id,School school);



}
