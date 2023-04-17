package demo.mathapp.DTO.Test;

import demo.mathapp.DTO.TestResult.GetTestResult;
import demo.mathapp.DTO.TestTask.GetTestTask;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Data
public class GetTest {
    private double testTime;
    private int maxTries;
    @Temporal(TemporalType.TIMESTAMP)
    private Date activationTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deactivationTime;
    //    private SchoolClass schoolClass;
    private List<GetTestTask> tasks;
    private List<GetTestResult> results;
}
