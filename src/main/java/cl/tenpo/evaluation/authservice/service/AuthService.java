package cl.tenpo.evaluation.authservice.service;

import cl.tenpo.evaluation.authservice.dto.JwtResponse;
import cl.tenpo.evaluation.authservice.dto.LoginRequest;
import cl.tenpo.evaluation.authservice.dto.SignupRequest;

public interface AuthService {
    JwtResponse signin(LoginRequest loginRequest);

    void signup(SignupRequest signUpRequest);
}

