package demo.mathapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//TODO usun password encoder i przenies beana do SecurityConfig
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
