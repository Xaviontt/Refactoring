package blps.lab2.dto.requests.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUserRequest {
    private String email;
    private String password;
}
