package demo.mathapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import demo.mathapp.SchoolType;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString(callSuper = true)
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private SchoolType schoolType;
    @Column(nullable = false)
    private int classYear;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "material", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<SchoolClass> classList;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "material",fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<MaterialTopic> materialTopics;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return  Objects.equals(this.getId(), material.getId()) &&
                Objects.equals(this.getClassList(), material.getClassList()) &&
                Objects.equals(this.getMaterialTopics(), material.getMaterialTopics()) &&
                Objects.equals(this.getClassYear(), material.getClassYear()) &&
                Objects.equals(this.getSchoolType(), material.getSchoolType());
    }
}
