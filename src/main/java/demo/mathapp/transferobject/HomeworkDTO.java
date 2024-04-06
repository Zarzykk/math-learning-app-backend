package demo.mathapp.transferobject;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class HomeworkDTO {

    private long id;
    private long maxWorkTime;
    private double maxPoints;
    private LocalDateTime activationTime;
    private LocalDateTime deactivationTime;
    private long schoolClassId;
}
