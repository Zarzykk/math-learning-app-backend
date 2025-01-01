package demo.mathapp.transferobject;

import demo.mathapp.transferobject.student.StudentDto;
import lombok.Data;

import java.util.List;

@Data
public class ClassDTO {

    private long id;
    private String classYearAndIndex;
    private List<StudentDto> students;
}
