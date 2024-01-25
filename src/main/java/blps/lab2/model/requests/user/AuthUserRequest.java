package blps.lab2.model.requests.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUserRequest {
    private String email;
    private String password;
}
