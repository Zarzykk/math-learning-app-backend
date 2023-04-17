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
@AllArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final ModelMapper modelMapper;

    public School createSchool(CreateSchool school) {
        return schoolRepository.save(modelMapper.map(school,School.class));
    }

    public School getSchoolById(Long id){
        return schoolRepository.findById(id).orElseThrow();
    }

    public List<GetSchoolName> getSchoolsByType(SchoolType type){
        return schoolRepository.findSchoolsBySchoolType(type)
                .stream()
                .map(school -> modelMapper.map(school,GetSchoolName.class))
                .collect(Collectors.toList());
    }

}
