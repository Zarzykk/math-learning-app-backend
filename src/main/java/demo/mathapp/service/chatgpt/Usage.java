package demo.mathapp.service.chatgpt;

import lombok.Data;

@Data
public class Usage{
    public int promptTokens;
    public int completionTokens;
    public int totalTokens;
}
