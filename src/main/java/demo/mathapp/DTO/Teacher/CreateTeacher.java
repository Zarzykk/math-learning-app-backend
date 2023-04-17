package demo.mathapp.DTO.Teacher;

import demo.mathapp.DTO.User.CreateUser;
import demo.mathapp.model.SchoolClass;
import lombok.Data;

import java.util.List;

@Data
public class CreateTeacher {
    private CreateUser user;
    private List<SchoolClass> classes;
}
