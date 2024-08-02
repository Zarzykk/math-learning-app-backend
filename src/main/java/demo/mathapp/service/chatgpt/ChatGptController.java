package demo.mathapp.service.chatgpt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/chatgpt")
@RestController
@RequiredArgsConstructor
public class ChatGptController {

    private final ChatGptApiClient chatGptApiClient;

    @PostMapping("/generate")
    public ResponseEntity<ChatGptTextCompletionResponse> generatePrompt(@RequestBody String prompt) {
        return ResponseEntity.ok(chatGptApiClient.generateText(prompt));
    }
}
