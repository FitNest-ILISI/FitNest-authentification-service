package fitnest.auth_service.services;


import fitnest.auth_service.dto.AuthenticationResponse;
import fitnest.auth_service.dto.AuthenticationResquest;
import fitnest.auth_service.dto.RegisterRequest;

public interface IAuthService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationResquest request);

    void sendPasswordResetToken(String username);

    void resetPassword(String token, String newPassword);
}