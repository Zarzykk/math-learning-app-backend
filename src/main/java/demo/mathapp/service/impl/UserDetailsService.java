package demo.mathapp.service.impl;

import demo.mathapp.CustomUserDetails;
import demo.mathapp.model.Student;
import demo.mathapp.model.Teacher;
import demo.mathapp.model.User;
import demo.mathapp.repository.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private CustomUserRepository userRepository;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with "));
        String role = "ROLE_";
        if (user instanceof Student){
            role += "STUDENT";
        } else if (user instanceof Teacher){
            role += "TEACHER";
        } else {
            role += "ADMIN";
        }
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));

//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
        return new CustomUserDetails(user.getEmail(),
                user.getPassword(),
                authorities,
                String.valueOf(user.getId()),
                user.getFirstName(),
                user.getLastName());
    }

}
