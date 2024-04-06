package demo.mathapp.transferobject;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HomeworkResultDTO {
    private long id;
    private double homeworkTimeResult;
    private double points;
    private long studentId;
    private boolean passed;
    private long homeworkId;
}
