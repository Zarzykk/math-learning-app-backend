package demo.mathapp.transferobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HomeworkResultTO {
    private long id;
    private double homeworkTimeResult;
    private double points;
    private long studentId;
    private boolean passed;
    private long homeworkId;
}
