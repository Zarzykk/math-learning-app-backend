package demo.mathapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "WORK_TYPE",
        discriminatorType = DiscriminatorType.STRING
)
public class WorkAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double points;
    @Lob
    @Column(columnDefinition = "text")
    private String answer;
    @ManyToOne
    private WorkResult result;
}
