package blps.lab2.controller.user;

import blps.lab2.dto.requests.user.AuthUserRequest;
import blps.lab2.dto.requests.user.RefreshUserRequest;
import blps.lab2.dto.responses.user.AuthUserResponse;
import blps.lab2.dto.responses.user.UserView;
import blps.lab2.service.user.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public UserView register(@RequestBody @Valid AuthUserRequest req) {
        return UserView.fromUser(authService.register(req));
    }

    @PostMapping("/login")
    public AuthUserResponse login(@RequestBody @Valid AuthUserRequest req) {
        return authService.login(req);
    }

    @PostMapping("/refresh")
    public AuthUserResponse refresh(@RequestBody @Valid RefreshUserRequest req) {
        return authService.refresh(req);
    }
}
