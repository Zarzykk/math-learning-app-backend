package demo.mathapp.transferobject;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Lob;

@Data
@NoArgsConstructor
public class HomeworkAnswerDTO {

    private long id;
    private double points;
    @Lob
    @Column(columnDefinition = "text")
    private String answer;
    private long homeworkResultId;
}
