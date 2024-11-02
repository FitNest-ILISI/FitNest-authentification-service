package fitnest.auth_service.web;

import fitnest.auth_service.dto.*;
import fitnest.auth_service.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authenticationService;

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

    @PostMapping("/request-password-reset")
    public ResponseEntity<String> requestPasswordReset(@RequestBody PasswordResetRequest request) {
        authenticationService.sendPasswordResetToken(request.getUsername());
        return ResponseEntity.ok("Password reset token sent.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordUpdateRequest request) {
        authenticationService.resetPassword(request.getToken(), request.getNewPassword());
        return ResponseEntity.ok("Password has been reset successfully.");
    }
}
