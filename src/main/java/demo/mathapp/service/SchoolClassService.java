package demo.mathapp.service;

import demo.mathapp.DTO.SchoolClass.CreateSchoolClass;
import demo.mathapp.model.School;
import demo.mathapp.model.SchoolClass;
import demo.mathapp.repository.SchoolClassRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SchoolClassService {
    List<SchoolClass> getSchoolClassesBySchoolName(String schoolName);

    SchoolClass createSchoolClass(SchoolClass schoolClass);

    SchoolClass updateSchoolClass(Long id,SchoolClass schoolClass);

    void deleteSchoolClass(Long classId);


}
