package demo.mathapp.transferobject;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoDTO {
    private String id;
    private String firstName;
    private String lastName;
}
