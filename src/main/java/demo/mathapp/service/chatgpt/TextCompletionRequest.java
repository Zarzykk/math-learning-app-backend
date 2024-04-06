package demo.mathapp.service.chatgpt;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextCompletionRequest {
    @JsonProperty("model")
    private String model;
    @JsonProperty("role")
    private String role;
    @JsonProperty("content")
    private String content;
}
