package fitnest.auth_service.services;
import fitnest.auth_service.entities.User;
import fitnest.auth_service.entities.Interest;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User addUser(User appuser);

    Optional<User> getUser(Long userId);

    List<Interest> getUserInterests(Long userId);
}


