package demo.mathapp.transferobject.test;


import demo.mathapp.transferobject.TaskDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TestTO {

    private long id;
    private Integer maxTries;
    private List<TaskDTO> tasks;
}
