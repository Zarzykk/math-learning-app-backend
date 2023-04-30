package demo.mathapp.mapper.school;

import demo.mathapp.DTO.School.CreateSchool;
import demo.mathapp.DTO.School.GetSchoolName;
import demo.mathapp.SchoolType;
import demo.mathapp.model.School;

public class SchoolMapper {

    private School dtoToEntity(CreateSchool dto) {
        School school = new School();
        school.setSchoolName(dto.getSchoolName());
        school.setSchoolType(SchoolType.valueOf(dto.getSchoolType()));
        return school;
    }

    private GetSchoolName getSchoolNameFromEntity(School school){
        return new GetSchoolName(school.getSchoolName());
    }
}
