package fitnest.auth_service.services;

import fitnest.auth_service.Repos.UserRepo;
import fitnest.auth_service.entities.User;
import fitnest.auth_service.entities.Interest; // Importing the Interest enum
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    // Add a new user
    public User addUser(User appuser) {
        return userRepo.save(appuser);
    }

    // Get user by ID
    public Optional<User> getUser(Long userId) {
        return userRepo.findById(userId);
    }

    // Get user interests
    public List<Interest> getUserInterests(Long userId) {
        return userRepo.findById(userId)
                .map(User::getInterests)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
