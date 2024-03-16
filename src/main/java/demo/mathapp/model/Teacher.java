package demo.mathapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.annotation.PreDestroy;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue(value = "TEACHER")
@ToString(callSuper = true)
public class Teacher extends User {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<SchoolClass> classes = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return  Objects.equals(this.getId(), teacher.getId()) &&
                Objects.equals(this.getFirstName(), teacher.getFirstName()) &&
                Objects.equals(this.getLastName(), teacher.getLastName()) &&
                Objects.equals(this.getPassword(), teacher.getPassword()) &&
                Objects.equals(this.getEmail(), teacher.getEmail());
    }

    @PreDestroy
    public void preDestroy(){
       classes.forEach(schoolClass -> schoolClass.setTeacher(null));
       this.classes = new ArrayList<>();
    }
}
