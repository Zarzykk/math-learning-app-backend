package demo.mathapp.transferobject;

import lombok.Data;
import lombok.NoArgsConstructor;

//TODO DTO zamiast TO
@Data
@NoArgsConstructor
public class AdminTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
