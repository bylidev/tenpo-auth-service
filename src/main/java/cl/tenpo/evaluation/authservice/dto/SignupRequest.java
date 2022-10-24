package cl.tenpo.evaluation.authservice.dto;

import cl.tenpo.evaluation.authservice.model.Role;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@Data
public class SignupRequest {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(max = 20)
    private String username;
    @NotBlank
    @Size(max = 120)
    private String password;
    @NotEmpty
    private List<Role> roleList;
}
