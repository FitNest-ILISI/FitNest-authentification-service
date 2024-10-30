package fitnest.auth_service;

import fitnest.auth_service.dto.AuthenticationResquest;
import fitnest.auth_service.dto.AuthenticationResponse;
import fitnest.auth_service.dto.RegisterRequest;
import fitnest.auth_service.services.AuthenticationService;
import fitnest.auth_service.web.AuthController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AuthControllerTests {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        RegisterRequest registerRequest = new RegisterRequest();
        AuthenticationResponse response = new AuthenticationResponse("jwt-token");

        when(authenticationService.register(registerRequest)).thenReturn(response);

        ResponseEntity<AuthenticationResponse> result = authController.register(registerRequest);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("jwt-token", result.getBody().getToken());
    }

    @Test
    void testAuthenticate() {
        AuthenticationResquest authRequest = new AuthenticationResquest();
        AuthenticationResponse response = new AuthenticationResponse("jwt-token");

        when(authenticationService.authenticate(authRequest)).thenReturn(response);

        ResponseEntity<AuthenticationResponse> result = authController.register(authRequest);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("jwt-token", result.getBody().getToken());
    }
}
