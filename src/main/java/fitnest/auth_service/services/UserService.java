package fitnest.auth_service.services;

import fitnest.auth_service.Repos.UserRepo;
import fitnest.auth_service.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public ResponseEntity<User> findUserById(Long id) {
        return userRepo.findById(id)
                .map(ResponseEntity::ok)  // If user is found, return 200 with user
                .orElseGet(() -> ResponseEntity.notFound().build());  // Else return 404
    }

    public ResponseEntity<User> findUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<User>> retrieveAllUsers() {
        List<User> users = userRepo.findAll();
        return ResponseEntity.ok(users);
    }
}
