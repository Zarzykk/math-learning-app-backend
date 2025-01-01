package demo.mathapp.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.mathapp.model.chatgpt.ConversationMessage;
import demo.mathapp.model.chatgpt.MessageResponse;
import demo.mathapp.service.chatgpt.ChatGptApiClient;
import demo.mathapp.service.chatgpt.ChatGptTextCompletionResponse;
import demo.mathapp.service.chatgpt.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatGptApiService {

    private final ChatGptApiClient apiClient;

    private final MessageService messageService;

    private final static String SYSTEM_MESSAGE_CONTENT = "Jesteś ekspertem matematycznym, który potrafi wymyśleć zadania z matematyki dla dzieci ze szkoły podstawowej, oraz ponadpodstawowej. Dostaniesz w poleceniu temat lub dział z jakiego ma być zadanie, Ty masz wygenerować treść zadania, rozwiązanie oraz krok po kroku przedstawione rozwiązanie. Chce żeby Twoje odpowiedzi były w formacie JSON, niech cały obiekt nazywa się 'assignment', w środku ma posiadać pola: 'content', w którym będzie treść zadania, pole 'answer' z poprawną odpowiedzią do tego zadania, oraz tablice 'hints', każdy obiekt hint ma mieć pole 'content', w którym będzie opisana wskazówka jak dalej rozwiązywać zadanie. Obiekty typu hint, będą wysyłane pojedynczo, gdy będziesz omawiał dany krok rozwiązania zadania, ale tylko jeżeli użykotnik Cię o to poprosi. Treść pola 'answer' powinna być taka sama jak ostatni krok w tablicy 'hints'. Przy wysyłaniu odpowiedzi pomijaj oznaczenie ```json```, ale prosze o zostawienie struktury JSON. Jeżeli chodzi o wypisywanie matematycznych równań chciałbym żebyś zapisywał je w formacie KaTeX, ale tak żebym mógł później bez problemów sparsować JSON.";
//    private final static String SYSTEM_MESSAGE_TEACHER = "Jesteś ekspertem matematycznym, Twoim zadaniem jest wygenerować zadania do sprawdzianu z matematyki. Treść pytania będzie taka sama, Twoim zadaniem jest wygenerować zadanie z matematyki z działu podanego w zapytaniu. Zadania które wygenerujesz nie mogą być do siebie podobne, powinny chociaż trochę różnić się sposobem rozwiązania. Chce żeby Twoje odpowiedzi były w formacie JSON, niech każde zadanie będzie należało do tablicy  'tasks', w środku każdy obiekt 'task' ma posiadać pola: 'content', w którym będzie treść zadania, pole 'answer' z poprawną odpowiedzią do tego zadania. Przy wysyłaniu odpowiedzi pomijaj oznaczenie ```json```, ale prosze o zostawienie struktury JSON. Jeżeli chodzi o wypisywanie matematycznych równań chciałbym żebyś zapisywał je w formacie KaTeX, ale tak żebym mógł później bez problemów sparsować JSON.";
    private final static String SYSTEM_MESSAGE_TEACHER = "Jesteś ekspertem matematycznym, Twoim zadaniem jest wygenerować zadania do sprawdzianu z matematyki. Treść pytania będzie taka sama, Twoim zadaniem jest wygenerować zadanie z matematyki z działu podanego w zapytaniu. Zadania które wygenerujesz nie mogą być do siebie podobne, powinny chociaż trochę różnić się sposobem rozwiązania. Jeżeli chodzi o wypisywanie matematycznych równań chciałbym żebyś zapisywał je w formacie LaTeX.";
    private final static Message SYSTEM_MESSAGE = new Message("system", SYSTEM_MESSAGE_TEACHER);

    //TODO: zmieniony został response, teraz trzeba na froncie zrobić formatke i obsłużyć to.S
    @Transactional
    public MessageResponse sendMessage(String prompt, String uuid) {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
        }
        ChatGptTextCompletionResponse chatGptTextCompletionResponse = callApi(prompt, uuid);
        ObjectMapper objectMapper = new ObjectMapper();
        MessageResponse messageResponse = null;
        try {
            messageResponse = objectMapper.readValue(chatGptTextCompletionResponse.getChoices().get(0).getMessage().getContent(), MessageResponse.class);
            messageResponse.setUuid(uuid);
        } catch (JsonProcessingException e) {
            System.out.println(chatGptTextCompletionResponse.getChoices().get(0).getMessage().getContent());
            throw new RuntimeException(e);
        }
        return messageResponse;
    }

    private ChatGptTextCompletionResponse callApi(String prompt, String uuid) {
        List<Message> messages  = new ArrayList<>();
        messages.add(SYSTEM_MESSAGE);
        messages.addAll(messageService.getMessages(uuid).stream()
                .map(conversationMessage -> new Message(conversationMessage.getRole(), conversationMessage.getContent()))
                .toList());

        messages.add(new Message("user", prompt));

        ChatGptTextCompletionResponse chatGptTextCompletionResponse = apiClient.sendMessage(messages);
        messages.add(chatGptTextCompletionResponse.getChoices().get(0).getMessage());
        saveMessages(uuid, messages);

        return chatGptTextCompletionResponse;
    }

    private void saveMessages(String uuid, List<Message> messages) {
        messages.remove(SYSTEM_MESSAGE);
        List<ConversationMessage> conversationMessages = messages.stream().map(message -> ConversationMessage.builder()
                .conversationId(uuid)
                .role(message.getRole())
                .content(message.getContent())
                .build()).toList();
        messageService.saveAll(conversationMessages);
    }

}
