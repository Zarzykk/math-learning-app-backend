package demo.mathapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoder {
    @Bean
    public org.springframework.security.crypto.password.PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    public String encodePassword(String password){
        return encoder().encode(password);
    }

}
