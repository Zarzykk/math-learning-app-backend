package demo.mathapp.DTO.Test;


import demo.mathapp.DTO.TestTask.CreateTestTask;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Data
public class CreateTest {
    private double testTime;
    private int maxTries;
    @Temporal(TemporalType.TIMESTAMP)
    private Date activationTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deactivationTime;
    private double points;
//    private SchoolClass schoolClass;
    private List<CreateTestTask> tasks;
}
