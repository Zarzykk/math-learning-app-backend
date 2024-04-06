package demo.mathapp.model;

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
public class MaterialTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
    private Material material;
    @OneToMany(mappedBy = "materialTopic", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Task> tasks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialTopic materialTopic = (MaterialTopic) o;
        return Objects.equals(this.getId(), materialTopic.getId()) &&
               Objects.equals(this.getName(), materialTopic.getName()) &&
               Objects.equals(this.getMaterial(), materialTopic.getMaterial()) &&
               Objects.equals(this.getTasks(), materialTopic.getTasks());

    }
}
