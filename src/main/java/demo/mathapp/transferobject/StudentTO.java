package demo.mathapp.transferobject;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class StudentTO {

    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private long schoolClassId;
}
