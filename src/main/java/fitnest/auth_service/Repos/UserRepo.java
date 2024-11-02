package fitnest.auth_service.Repos;

import fitnest.auth_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

}
