package demo.mathapp.transferobject;

import demo.mathapp.ClassYear;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SchoolClassTO {

    private long id;

    private ClassYear classYear;

    private String classIndex;

    private long teacherId;

    private long schoolId;

    private long materialId;
}
