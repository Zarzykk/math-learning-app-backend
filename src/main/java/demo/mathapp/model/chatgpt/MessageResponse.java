package demo.mathapp.model.chatgpt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {

//    private Assignment assignment;
    private List<Assignment> tasks;
    private String uuid;
}
