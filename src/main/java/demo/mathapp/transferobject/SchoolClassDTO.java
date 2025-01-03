package demo.mathapp.transferobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchoolClassDTO {

    private Long id;

    private int classYear;

    private String classIndex;

    private Long teacherId;

    private Long schoolId;
}
