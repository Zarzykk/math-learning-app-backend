package demo.mathapp.mapper.schoolClass;

import demo.mathapp.DTO.SchoolClass.CreateSchoolClass;
import demo.mathapp.model.School;
import demo.mathapp.model.SchoolClass;
import demo.mathapp.model.Teacher;

public class SchoolClassMapper {

    private SchoolClass dtoToEntity(CreateSchoolClass dto) {
        SchoolClass entity = new SchoolClass();
        entity.setSchool(new School(dto.getSchoolId()));
        entity.setClassYear(dto.getClassYear());
        entity.setClassIndex(dto.getClassIndex());
        entity.setTeacher(new Teacher(dto.getTeacherId()));
        return entity;
    }

}
