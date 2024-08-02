package demo.mathapp.service.chatgpt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Choice{
    @JsonProperty("index")
    public int index;
    @JsonProperty("message")
    public Message message;
    @JsonProperty("logprobs")
    public Object logprobs;
    @JsonProperty("finish_reason")
    public String finishReason;
}