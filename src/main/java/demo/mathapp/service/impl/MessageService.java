package demo.mathapp.service.impl;

import demo.mathapp.model.chatgpt.ConversationMessage;
import demo.mathapp.repository.ConversationMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final ConversationMessageRepository messageRepository;

    public List<ConversationMessage> saveAll(List<ConversationMessage> messages) {
        return messageRepository.saveAll(messages);
    }

    public ConversationMessage buildMessage(String conversationId, String content, String role) {
        return ConversationMessage.builder()
                .content(content)
                .role(role)
                .conversationId(conversationId)
                .build();
    }

    public List<ConversationMessage> getMessages(String conversationId) {
        return messageRepository.findAllByConversationId(conversationId);
    }
}
