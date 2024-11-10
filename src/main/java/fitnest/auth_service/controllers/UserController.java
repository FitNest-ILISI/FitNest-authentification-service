package fitnest.auth_service.controllers;

import fitnest.auth_service.clients.AuthEventController;
import fitnest.auth_service.entities.Interest;
import fitnest.auth_service.entities.User;
import fitnest.auth_service.models.AuthEvent;
import fitnest.auth_service.services.IUserService; // Import de l'interface
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService; // Utilisation de l'interface
    private final AuthEventController eventController;

    // Endpoint to add a new user
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User createdUser = userService.addUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Endpoint to get a user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        Optional<User> userOptional = userService.getUser(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            List<AuthEvent> associatedEvents = eventController.findEventsByIdCoordinator(userId);

            user.setCreatedEvents(associatedEvents);

            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Endpoint to get a user's interests
    @GetMapping("/{userId}/interests")
    public ResponseEntity<List<Interest>> getUserInterests(@PathVariable Long userId) {
        List<Interest> interests = userService.getUserInterests(userId);
        return ResponseEntity.ok(interests);
    }
}
