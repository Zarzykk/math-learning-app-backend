package demo.mathapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MaterialTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
    private Material material;
    @OneToOne(mappedBy = "materialTopic")
    private Task task;
}
