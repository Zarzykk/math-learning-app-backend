package demo.mathapp.model.chatgpt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assignment {

    private String content;
    private String answer;
    private List<Hint> hints;
}
