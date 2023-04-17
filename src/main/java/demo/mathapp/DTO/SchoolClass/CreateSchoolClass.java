package demo.mathapp.DTO.SchoolClass;

import demo.mathapp.ClassYear;
import demo.mathapp.model.School;
import demo.mathapp.model.Teacher;
import lombok.Data;

@Data
public class CreateSchoolClass {
    private ClassYear classYear;
    private String classIndex;
    private Teacher teacher;
    private School school;
}
