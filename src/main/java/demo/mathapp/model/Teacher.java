package demo.mathapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = {CascadeType.ALL})
    private User user;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<SchoolClass> classes;

    public Teacher(long id) {
        this.id = id;
    }
}
