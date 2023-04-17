package demo.mathapp.DTO.Student;

import demo.mathapp.DTO.User.GetUserTestInfo;
import lombok.Data;

@Data
public class GetStudentTestInfo {
    private GetUserTestInfo user;
}
