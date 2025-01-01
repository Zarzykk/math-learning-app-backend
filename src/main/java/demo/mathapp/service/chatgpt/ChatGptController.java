package demo.mathapp.service.chatgpt;

import demo.mathapp.model.chatgpt.MessageResponse;
import demo.mathapp.service.impl.ChatGptApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/chatgpt")
@RestController
@RequiredArgsConstructor
public class ChatGptController {

    private final ChatGptApiService chatGptApiService;

    @PostMapping("/generate")
    public ResponseEntity<MessageResponse> startConversation(@RequestBody String prompt) {
        return ResponseEntity.ok(chatGptApiService.sendMessage(prompt, null));
    }

    @PostMapping("/generate/{uuid}")
    public ResponseEntity<MessageResponse> continueConversation(@RequestBody String prompt, String uuid) {
        return ResponseEntity.ok(chatGptApiService.sendMessage(prompt, uuid));
    }
}
