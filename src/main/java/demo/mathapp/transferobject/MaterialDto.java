package demo.mathapp.transferobject;

import demo.mathapp.SchoolType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class MaterialDto {

    private long id;
    private SchoolType schoolType;
    private int classYear;
    private String section;

}
