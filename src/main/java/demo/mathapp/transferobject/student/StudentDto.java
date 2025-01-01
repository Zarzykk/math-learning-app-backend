package demo.mathapp.transferobject.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String classIndex;

    private Long schoolClassId;
}
