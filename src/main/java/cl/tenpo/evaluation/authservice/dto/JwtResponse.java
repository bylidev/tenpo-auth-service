package cl.tenpo.evaluation.authservice.dto;

import cl.tenpo.evaluation.authservice.model.Role;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class JwtResponse {

    private String token;
    private String username;
    private String email;
    private List<Role> roles;

}
