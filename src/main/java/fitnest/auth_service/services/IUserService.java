package fitnest.auth_service.services;
import fitnest.auth_service.dto.UserDto;
import fitnest.auth_service.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User addUser(UserDto userDto);

    Optional<User> getUser(Long userId);
    Optional<String> getUserNameById(Long userId);
    List<String> getUserInterests(Long userId);
    User updateUser(Long userId, User updatedUser);
}


