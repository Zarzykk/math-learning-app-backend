package demo.mathapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import demo.mathapp.SchoolType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class School {
    public School(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private SchoolType schoolType;
    @Column(unique = true)
    private String schoolName;
    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<SchoolClass> schoolClasses;

}
