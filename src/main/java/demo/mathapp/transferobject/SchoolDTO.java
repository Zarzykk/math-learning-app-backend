package demo.mathapp.transferobject;

import demo.mathapp.SchoolType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SchoolDTO {

    private Long id;
    private SchoolType schoolType;
    private String schoolName;

}
