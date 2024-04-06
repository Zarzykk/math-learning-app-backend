package demo.mathapp.service.chatgpt;


import lombok.Data;

import java.util.List;

@Data
public class ChatGptTextCompletionResponse {
    public String id;
    public String object;
    public int created;
    public String model;
    public String systemFingerprint;
    public List<Choice> choices;
    public Usage usage;
}
