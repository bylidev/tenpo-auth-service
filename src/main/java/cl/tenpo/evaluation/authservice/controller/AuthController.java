package cl.tenpo.evaluation.authservice.controller;

import cl.tenpo.evaluation.authservice.components.JwtUtils;
import cl.tenpo.evaluation.authservice.dto.JwtResponse;
import cl.tenpo.evaluation.authservice.dto.LoginRequest;
import cl.tenpo.evaluation.authservice.dto.SignupRequest;
import cl.tenpo.evaluation.authservice.service.AuthService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static io.swagger.v3.oas.annotations.enums.SecuritySchemeIn.HEADER;

@RestController
@RequestMapping("/api/v1/auth")
@Validated
@OpenAPIDefinition(servers = {
        @Server(url = "https://api.byli.dev/auth-service", description = "byli.dev")
})
@SecurityScheme(name ="Bearer Authentication", in = HEADER, type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signin")
    public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
       return this.authService.signin(loginRequest);
    }

    @PostMapping("/signup")
    public void registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        this.authService.signup(signUpRequest);
    }

    @PostMapping(value = "/validate")
    @SecurityRequirement(name = "Bearer Authentication")
    public void validateToken(HttpServletRequest request) {
        this.jwtUtils.validateJwtToken(request);
    }

}
