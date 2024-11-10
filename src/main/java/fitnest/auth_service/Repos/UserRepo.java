package fitnest.auth_service.Repos;

import fitnest.auth_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
@Repository
public interface UserRepo extends JpaRepository<User,Long> {

}
