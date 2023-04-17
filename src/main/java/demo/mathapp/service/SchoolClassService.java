package demo.mathapp.service;

import demo.mathapp.DTO.SchoolClass.CreateSchoolClass;
import demo.mathapp.model.SchoolClass;
import demo.mathapp.repository.SchoolClassRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SchoolClassService {
    private final MaterialService materialService;
    private final SchoolService schoolService;

    private final SchoolClassRepository schoolClassRepository;
    private final ModelMapper modelMapper;

    public SchoolClass createSchoolClass(CreateSchoolClass createSchoolClass){
        SchoolClass schoolClass = modelMapper.map(createSchoolClass, SchoolClass.class);
        schoolClass.setMaterial(materialService.getMaterialByYear(
                schoolService.getSchoolById(schoolClass.getSchool().getId()).getSchoolType(),
                schoolClass.getClassYear()
        ));
        return schoolClassRepository.save(schoolClass);
    }
}
