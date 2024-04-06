package demo.mathapp.service.chatgpt;

import lombok.Data;

@Data
public class Choice{
    public int index;
    public Message message;
    public Object logprobs;
    public String finishReason;
}