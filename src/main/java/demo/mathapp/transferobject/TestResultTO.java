package demo.mathapp.transferobject;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TestResultTO {
    private long id;
    private double workTimeResult;
    private double points;
    private long studentId;
    private boolean passed;
    private long testId;
}
