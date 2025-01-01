package demo.mathapp.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double maxPoints;

    private LocalDateTime activationTime;

    private LocalDateTime deactivationTime;

    @Enumerated(value = EnumType.STRING)
    private AssignmentType type;

    @ManyToOne
    @JoinColumn(name = "CLASS_ID")
    private SchoolClass schoolClass;

    @ManyToOne
    @JoinColumn(name = "MATERIAL_ID")
    private Material material;
}
