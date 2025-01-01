package demo.mathapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double points;

    private Boolean passed;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID")
    private Student student;

    @OneToOne
    @JoinColumn(name = "ASSIGNMENT_ID")
    private Assignment assignment;

}
