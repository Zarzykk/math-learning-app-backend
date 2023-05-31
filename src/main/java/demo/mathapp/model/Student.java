package demo.mathapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Data
@DiscriminatorValue(value = "STUDENT")
public class Student extends User{

    @ManyToOne
    private SchoolClass studentClass;
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<WorkResult> workResults;
}
