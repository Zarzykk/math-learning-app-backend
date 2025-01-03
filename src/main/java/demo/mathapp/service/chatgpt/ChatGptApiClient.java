package demo.mathapp.service.chatgpt;


import demo.mathapp.config.ChatGptApiConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatGptApiClient {

    private final ChatGptApiConfiguration configuration;
    private final RestTemplate restTemplate;

    public ChatGptTextCompletionResponse sendMessage(List<Message> messages) {
        return  sendChatGptApiRequest("/completions", HttpMethod.POST, TextCompletionRequest.class,
                createTextCompletionRequestBody(messages), ChatGptTextCompletionResponse.class);
    }


    private <R, B> R sendChatGptApiRequest(String endpoint, HttpMethod httpMethod, Class<B> requestBodyType, B requestBody, Class<R> responseType) {
        final String url = configuration.getUrl();
        final String key = configuration.getKey();
        RequestEntity<B> requestEntity = RequestEntity
                .method(httpMethod, getChatGptApiUri(url, endpoint))
                .headers(getHttpHeaders(key))
                .body(requestBody);
        return restTemplate.exchange(requestEntity, responseType).getBody();
    }

    private HttpHeaders getHttpHeaders(String key) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", createKeyHeaderValue(key));
        return new HttpHeaders(headers);
    }

    private String createKeyHeaderValue(String key) {
        return "Bearer " + key;
    }

    private TextCompletionRequest createTextCompletionRequestBody(List<Message> messages) {
        return new TextCompletionRequest("gpt-4o", messages);
    }

    private URI getChatGptApiUri(String url, String endpoint) {
        return URI.create(url + endpoint);
    }

}
