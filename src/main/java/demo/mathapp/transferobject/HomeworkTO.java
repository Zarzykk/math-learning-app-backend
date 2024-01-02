package demo.mathapp.transferobject;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@NoArgsConstructor
public class HomeworkTO {

    private long id;
    private long maxWorkTime;
    private double maxPoints;
    @Temporal(TemporalType.TIMESTAMP)
    private Date activationTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deactivationTime;
    private long schoolClassId;
}
