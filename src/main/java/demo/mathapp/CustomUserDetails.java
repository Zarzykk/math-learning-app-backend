package demo.mathapp;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class CustomUserDetails extends User {

    private String id;
    private String firstName;
    private String lastName;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String id, String firstName, String lastName) {
        super(username, password, authorities);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
