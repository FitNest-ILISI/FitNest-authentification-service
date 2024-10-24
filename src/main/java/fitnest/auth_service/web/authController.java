package fitnest.auth_service.web;

import fitnest.auth_service.dto.AuthenticationResponse;
import fitnest.auth_service.dto.AuthenticationResquest;
import fitnest.auth_service.dto.RegisterRequest;
import fitnest.auth_service.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class authController {

    @Autowired
    private  AuthenticationService authenticationService;

    @GetMapping("/hello")
    public String hello(){
        return "hello there";
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register
            (@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register
            (@RequestBody AuthenticationResquest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
