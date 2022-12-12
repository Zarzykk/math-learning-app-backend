package demo.mathapp.service;

import demo.mathapp.DTO.UserDTO;
import demo.mathapp.DTO.UserInfoDTO;
import demo.mathapp.model.User;
import demo.mathapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserInfoDTO> getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .stream()
                .map(user -> modelMapper.map(user,UserInfoDTO.class))
                .collect(Collectors.toList());
    }

    public List<UserInfoDTO> getUsersByRole(String role) {
        return userRepository.findAllByRole(role.toUpperCase(Locale.ROOT))
                .stream()
                .map(user -> modelMapper.map(user,UserInfoDTO.class))
                .collect(Collectors.toList());
    }

    public User createUser(UserDTO userDTO) {
        return userRepository.save(modelMapper.map(userDTO, User.class));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserInfoDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user,UserInfoDTO.class))
                .collect(Collectors.toList());
    }
}
