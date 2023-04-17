package demo.mathapp.service;

import demo.mathapp.DTO.User.CreateUser;
import demo.mathapp.DTO.User.GetUserTestInfo;
import demo.mathapp.model.User;
import demo.mathapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public List<GetUserTestInfo> getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .stream()
                .map(user -> modelMapper.map(user, GetUserTestInfo.class))
                .collect(Collectors.toList());
    }

    public List<GetUserTestInfo> getUsersByRole(String role) {
        return userRepository.findAllByRole(role.toUpperCase(Locale.ROOT))
                .stream()
                .map(user -> modelMapper.map(user, GetUserTestInfo.class))
                .collect(Collectors.toList());
    }

    public User createUser(CreateUser createUser){

        User user = modelMapper.map(createUser,User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

//    public User createUser(UserDTO userDTO) {
//        return userRepository.save(modelMapper.map(userDTO, User.class));
//    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<GetUserTestInfo> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, GetUserTestInfo.class))
                .collect(Collectors.toList());
    }
}
