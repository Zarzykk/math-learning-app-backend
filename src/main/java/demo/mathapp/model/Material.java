package demo.mathapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import demo.mathapp.SchoolType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private SchoolType schoolType;
    @Column(nullable = false)
    private int classYear;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "material",fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    private List<SchoolClass> classList;
    @OneToMany(mappedBy = "material",fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    private List<MaterialTopic> materialTopics;

}
