package demo.mathapp.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String description;

    private double points;

    @Enumerated(value = EnumType.STRING)
    private TaskType type;

    @JoinColumn(name = "MATERIAL_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Material material;



}
