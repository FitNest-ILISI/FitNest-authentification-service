package fitnest.auth_service.web;

import fitnest.auth_service.entities.User;
import fitnest.auth_service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/id")
    public ResponseEntity<User> getUserById(@RequestParam Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("/email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        return userService.findUserByEmail(email);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.retrieveAllUsers();
    }
}
