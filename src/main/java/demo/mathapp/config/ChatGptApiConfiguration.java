package demo.mathapp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("application.config.api.chatgpt")
@Getter
@Setter
public class ChatGptApiConfiguration {

    private String url;
    private String key;

}
