package demo.mathapp.transferobject;

import demo.mathapp.transferobject.student.StudentTO;
import lombok.Data;

import java.util.List;

@Data
public class ClassDTO {

    private long id;
    private String classYearAndIndex;
    private List<StudentTO> students;
}
