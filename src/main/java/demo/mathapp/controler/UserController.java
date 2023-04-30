package demo.mathapp.controler;

import demo.mathapp.DTO.User.GetUserTestInfo;
import demo.mathapp.DTO.User.UserDTO;
import demo.mathapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/role/{userRole}")
    public List<UserDTO> getUsersByRole(@PathVariable String userRole) {
        return userService.getUsersByRole(userRole);
    }

    @GetMapping("/user/email/{userEmail}")
    public Optional<UserDTO> getUserByEmail(@PathVariable String userEmail) {
        return userService.getUserByEmail(userEmail);
    }

    @GetMapping
    public List<GetUserTestInfo> getUsers() {
        return userService.getUsers();
    }

//    @PostMapping
//    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
//        return ResponseEntity.status(201).body(
//                userService.createUser(userDTO)
//        );
//    }

    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
