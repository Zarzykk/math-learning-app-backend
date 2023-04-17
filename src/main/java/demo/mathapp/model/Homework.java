package demo.mathapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Homework {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private Date deadline;

    @ManyToOne
    private SchoolClass schoolClass;
    @OneToMany(mappedBy = "homework",fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    private List<Task> tasks;
    @OneToMany(mappedBy = "homework",fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    private List<HomeworkResult> homeworkResults;
}
