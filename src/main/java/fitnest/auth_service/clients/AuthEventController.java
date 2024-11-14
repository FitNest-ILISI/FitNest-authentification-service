package fitnest.auth_service.clients;

import fitnest.auth_service.models.AuthEvent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@CrossOrigin(origins = "*")
@FeignClient(name = "EVENT-SERVICE", url = "http://localhost:8888")
public interface AuthEventController {
    @GetMapping("/api/events/associated/{userid}")
    ArrayList<AuthEvent> findEventsByIdCoordinator(@PathVariable("userid") Long userid);
}
