package cl.tenpo.evaluation.authservice.controller;

import cl.tenpo.evaluation.authservice.components.JwtUtils;
import cl.tenpo.evaluation.authservice.dto.JwtResponse;
import cl.tenpo.evaluation.authservice.dto.LoginRequest;
import cl.tenpo.evaluation.authservice.dto.SignupRequest;
import cl.tenpo.evaluation.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@Validated
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

    @PostMapping("/token")
    public void validateToken(HttpServletRequest request) {
        this.jwtUtils.validateJwtToken(request);
    }

}
