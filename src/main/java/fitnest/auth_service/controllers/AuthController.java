package fitnest.auth_service.controllers;

import fitnest.auth_service.dto.*;
import fitnest.auth_service.services.IAuthService; // Import de l'interface
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService authenticationService; // Utilisation de l'interface

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        try {
            AuthenticationResponse response = authenticationService.register(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Si une erreur survient, retournez une réponse avec un message d'erreur dans AuthenticationResponse
            AuthenticationResponse errorResponse = new AuthenticationResponse();
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            AuthenticationResponse response = authenticationService.authenticate(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    AuthenticationResponse.builder()
                            .token(null)
                            .user_id(null)
                            .build()
            );
        }
    }


    @PostMapping("/request-password-reset")
    public ResponseEntity<AuthenticationResponse> requestPasswordReset(@RequestBody PasswordResetRequest request) {
        try {
            authenticationService.sendPasswordResetToken(request.getUsername());
            AuthenticationResponse successResponse = new AuthenticationResponse();
            return ResponseEntity.ok(successResponse);
        } catch (Exception e) {
            AuthenticationResponse errorResponse = new AuthenticationResponse();
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<AuthenticationResponse> resetPassword(@RequestBody PasswordUpdateRequest request) {
        try {
            authenticationService.resetPassword(request.getToken(), request.getNewPassword());
            AuthenticationResponse successResponse = new AuthenticationResponse();
            return ResponseEntity.ok(successResponse);
        } catch (Exception e) {
            AuthenticationResponse errorResponse = new AuthenticationResponse();
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
