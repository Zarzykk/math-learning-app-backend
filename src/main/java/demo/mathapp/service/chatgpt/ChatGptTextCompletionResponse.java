package demo.mathapp.service.chatgpt;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatGptTextCompletionResponse {
    @JsonProperty("id")
    public String id;
    @JsonProperty("object")
    public String object;
    @JsonProperty("created")
    public int created;
    @JsonProperty("model")
    public String model;
    @JsonProperty("system_fingerprint")
    public String systemFingerprint;
    @JsonProperty("choices")
    public List<Choice> choices;
    @JsonProperty("usage")
    public Usage usage;
}
