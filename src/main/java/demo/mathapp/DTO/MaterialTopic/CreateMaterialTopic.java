package demo.mathapp.DTO.MaterialTopic;

import demo.mathapp.ClassYear;
import demo.mathapp.SchoolType;
import lombok.Data;

@Data
public class CreateMaterialTopic {
    private SchoolType schoolType;
    private ClassYear classYear;

}
