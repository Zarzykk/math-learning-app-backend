package demo.mathapp.transferobject;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class HomeworkTO {

    private long id;
    private long maxWorkTime;
    private double maxPoints;
    private LocalDateTime activationTime;
    private LocalDateTime deactivationTime;
    private long schoolClassId;
}
