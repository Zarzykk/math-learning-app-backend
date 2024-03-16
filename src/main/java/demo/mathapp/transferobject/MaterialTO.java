package demo.mathapp.transferobject;

import demo.mathapp.SchoolType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MaterialTO {
    private long id;
    private SchoolType schoolType;
    private int classYear;

}
