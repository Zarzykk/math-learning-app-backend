package demo.mathapp.transferobject;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskTO {

    private long id;

    private String text;

    private double points;

    private long workId;

    private long materialTopicId;

}
