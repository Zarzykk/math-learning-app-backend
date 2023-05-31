package demo.mathapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "WORK_TYPE",
        discriminatorType = DiscriminatorType.STRING
)
public class WorkResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double workTimeResult;
    private double points;
    @ManyToOne
    private Student student;
    private boolean passed;
}
