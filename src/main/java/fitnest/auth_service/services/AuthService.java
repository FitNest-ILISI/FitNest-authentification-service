package fitnest.auth_service.services;

import fitnest.auth_service.Repos.AccountRepo;
import fitnest.auth_service.entities.Account;
import fitnest.auth_service.dto.AuthenticationResponse;
import fitnest.auth_service.dto.AuthenticationResquest;
import fitnest.auth_service.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AccountRepo accountRepo ;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = Account.builder()
                .username(request.getUsename())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        accountRepo
                .save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationResquest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
        );
        //usename and pw are correct
        var user = accountRepo
                .findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public void sendPasswordResetToken(String username) {
        var user = accountRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        var resetToken = jwtService.generateToken(user);
    }

    public void resetPassword(String token, String newPassword) {
        var username = jwtService.extractUsername(token);
        var user = accountRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        accountRepo.save(user);
    }
}
