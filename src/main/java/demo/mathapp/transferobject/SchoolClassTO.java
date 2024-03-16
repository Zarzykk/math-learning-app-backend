package demo.mathapp.transferobject;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SchoolClassTO {

    private long id;

    private int classYear;

    private String classIndex;

    private long teacherId;

    private long schoolId;

    private long materialId;
}
