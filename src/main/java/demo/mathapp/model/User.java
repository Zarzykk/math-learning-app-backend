package demo.mathapp.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String secondName;
    private String email;
    private String role;
    @OneToOne(mappedBy = "user",cascade = {CascadeType.ALL})
    private Teacher teacher;
    @OneToOne(mappedBy = "user",cascade = {CascadeType.ALL})
    private Student student;

}
