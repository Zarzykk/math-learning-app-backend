package demo.mathapp.transferobject;

import demo.mathapp.model.AssignmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@Builder
public class AssignmentDto {

    private Long id;

    private double maxPoints;

    private LocalDateTime activationTime;

    private LocalDateTime deactivationTime;

    private AssignmentType type;

    private Long schoolClassId;

    private String classIndex;

    private String materialSection;

    private Long materialId;


}
