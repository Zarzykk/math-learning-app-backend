package demo.mathapp.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "USER_TYPE",
        discriminatorType = DiscriminatorType.STRING
)
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    @Column(unique = true)
    private String email;
    @NotEmpty
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(this.getId(), user.getId()) &&
                Objects.equals(this.getFirstName(), user.getFirstName()) &&
                Objects.equals(this.getLastName(), user.getLastName()) &&
                Objects.equals(this.getPassword(), user.getPassword()) &&
                Objects.equals(this.getEmail(), user.getEmail());
    }
}
