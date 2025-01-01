package demo.mathapp.model;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class TaskStudentKey implements Serializable {

    private Long taskId;

    private Long studentId;

}
