package demo.mathapp.controler;

import demo.mathapp.DTO.UserInfoDTO;
import demo.mathapp.model.User;
import demo.mathapp.DTO.UserDTO;
import demo.mathapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/role/{userRole}")
    public List<UserInfoDTO> getUsersByRole(@PathVariable String userRole) {
        return userService.getUsersByRole(userRole);
    }

    @GetMapping("/user/email/{userEmail}")
    public List<UserInfoDTO> getUserByEmail(@PathVariable String userEmail) {
        return userService.getUserByEmail(userEmail);
    }

    @GetMapping
    public List<UserInfoDTO> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.status(201).body(
                userService.createUser(userDTO)
        );
    }

    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
