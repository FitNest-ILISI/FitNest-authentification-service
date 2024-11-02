package fitnest.auth_service.web;

import fitnest.auth_service.entities.Interest;
import fitnest.auth_service.entities.User;
import fitnest.auth_service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Endpoint to add a new user
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User createdUser = userService.addUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Endpoint to get a user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        Optional<User> user = userService.getUser(userId);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint to get a user's interests
    @GetMapping("/{userId}/interests")
    public ResponseEntity<List<Interest>> getUserInterests(@PathVariable Long userId) {
        List<Interest> interests = userService.getUserInterests(userId);
        return ResponseEntity.ok(interests);
    }
}
