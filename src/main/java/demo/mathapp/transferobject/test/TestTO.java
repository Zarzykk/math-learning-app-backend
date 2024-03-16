package demo.mathapp.transferobject.test;


import demo.mathapp.transferobject.TaskTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class TestTO {

    private long id;
    private Integer maxTries;
    private List<TaskTO> tasks;
}
