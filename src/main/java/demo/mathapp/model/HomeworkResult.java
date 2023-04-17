package demo.mathapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class HomeworkResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @OneToMany(mappedBy = "homeworkResult",fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    private List<HomeworkAnswer> answers;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Homework homework;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Student student;
}
