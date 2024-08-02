package demo.mathapp.service.chatgpt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message{
    @JsonProperty("role")
    private String role;
    @JsonProperty("content")
    private String content;
}