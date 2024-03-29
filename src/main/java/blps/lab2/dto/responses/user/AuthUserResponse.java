package blps.lab2.dto.responses.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthUserResponse {
    private String accessToken;
    private Long expiresIn;
    private String refreshToken;
}
