package cl.tenpo.evaluation.authservice.components;

import cl.tenpo.evaluation.authservice.exception.BadRequestException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Component
@Slf4j
public class JwtUtils {
    @Value("${cl.tenpo.security.jwt.secret}")
    private String jwtSecret;

    @Value("${cl.tenpo.security.session.lifetimeMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
            .setSubject((userPrincipal.getUsername()))
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public void validateJwtToken(HttpServletRequest request) {
        this.getJwtFromHeader(request).ifPresentOrElse(jwt -> this.validateJwtToken(jwt), ()-> {
        throw new UnsupportedJwtException("Authentication header is not present.");});
    }

    public void validateJwtToken(String authToken) {
        Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
    }

    public Optional<String> getJwtFromHeader(HttpServletRequest request) {
        Optional<String> jwt = Optional.empty();
        String authorization = request.getHeader("Authorization");
        if (!Objects.isNull(authorization)) {
            jwt = Optional.of(authorization.replace("Bearer ", ""));
        }
        return jwt;
    }
}
