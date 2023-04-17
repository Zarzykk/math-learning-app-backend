package demo.mathapp.DTO.User;

import lombok.Data;

@Data
public class CreateUser {
    private String firstName;
    private String secondName;
    private String email;
    private String role;
    private String password;
}
