package fitnest.auth_service.services.ImplServices;

import fitnest.auth_service.Repos.UserRepo;
import fitnest.auth_service.entities.User;
import fitnest.auth_service.entities.Interest; // Importing the Interest enum
import fitnest.auth_service.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepo userRepo;

    // Add a new user
    @Override
    public User addUser(User appuser) {
        return userRepo.save(appuser);
    }

    // Get user by ID
    @Override
    public Optional<User> getUser(Long userId) {
        return userRepo.findById(userId);
    }

    // Get user interests
    @Override
    public List<Interest> getUserInterests(Long userId) {
        return userRepo.findById(userId)
                .map(User::getInterests)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
