package demo.mathapp.DTO.User;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
public class UserDTO {
    @NotNull
    private String firstName;
    @NotNull
    private String secondName;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String role;
    @NotNull
    private String password;
}
