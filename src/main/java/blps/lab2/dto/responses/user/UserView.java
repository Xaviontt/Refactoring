package blps.lab2.dto.responses.user;

import blps.lab2.model.user.User;
import blps.lab2.model.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class UserView {
    private long id;
    private String email;
    private UserRole role;
    private Integer remainingGrades;
    private Date createdAt;

    public static UserView fromUser(User user) {
        return new UserView(user.getId(),
                user.getEmail(),
                user.getRole(),
                user.getRemainingGrades(),
                user.getCreatedAt()
        );
    }
}
