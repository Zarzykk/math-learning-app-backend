package demo.mathapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue(value = "TEACHER")
public class Teacher extends User {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<SchoolClass> classes;

}
