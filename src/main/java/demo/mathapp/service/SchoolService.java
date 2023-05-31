package demo.mathapp.service;

import demo.mathapp.DTO.School.CreateSchool;
import demo.mathapp.DTO.School.GetSchoolName;
import demo.mathapp.SchoolType;
import demo.mathapp.model.School;
import demo.mathapp.repository.SchoolRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public interface SchoolService {
    List<School> getSchoolsBySchoolType(SchoolType schoolType);

    School createSchool(School school);

    void deleteSchool(Long id);

    School updateSchool(Long id,School school);



}
