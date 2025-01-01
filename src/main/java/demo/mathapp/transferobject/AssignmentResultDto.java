package demo.mathapp.transferobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AssignmentResultDto {

    private Long id;

    private Double points;

    private Boolean passed;

    private Long studentId;

    private String studentName;



}
