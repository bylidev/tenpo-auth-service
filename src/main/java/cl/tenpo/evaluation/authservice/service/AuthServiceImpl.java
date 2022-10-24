package cl.tenpo.evaluation.authservice.service;

import cl.tenpo.evaluation.authservice.components.JwtUtils;
import cl.tenpo.evaluation.authservice.components.UserDetailsImpl;
import cl.tenpo.evaluation.authservice.dto.JwtResponse;
import cl.tenpo.evaluation.authservice.dto.LoginRequest;
import cl.tenpo.evaluation.authservice.dto.SignupRequest;
import cl.tenpo.evaluation.authservice.exception.BadRequestException;
import cl.tenpo.evaluation.authservice.model.Role;
import cl.tenpo.evaluation.authservice.model.User;
import cl.tenpo.evaluation.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public JwtResponse signin(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<Role> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList())
            .stream().map(r -> Role.valueOf(r)).collect(Collectors.toList());

        return JwtResponse.builder()
            .token(jwt)
            .username(userDetails.getUsername())
            .roles(roles)
            .email(userDetails.getEmail())
            .build();
    }

    @Override
    public void signup(SignupRequest signUpRequest) {

        userRepository.findByUsername(signUpRequest.getUsername()).ifPresent(u->{throw new BadRequestException("Username already taken.");});
        userRepository.findByUsername(signUpRequest.getEmail()).ifPresent(u->{throw new BadRequestException("Email already in use.");});

        userRepository.save(User.builder()
            .username(signUpRequest.getUsername())
            .email(signUpRequest.getEmail())
            .password(this.passwordEncoder.encode(signUpRequest.getPassword()))
            .roles(signUpRequest.getRoleList())
            .build());
    }

}
