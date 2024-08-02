package demo.mathapp.model.token;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("jwt.token")
@Getter
@Setter
public class JwtTokenConfiguration {

    private String secret;
    private String expiration;
}
