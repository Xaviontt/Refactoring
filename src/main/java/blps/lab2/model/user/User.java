package blps.lab2.model.user;

import blps.lab2.model.topic.Topic;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Email
    private String email;
    private String password;
    private UserRole role;
    private Integer remainingGrades;
    private Date createdAt;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Topic> topics;

    public User(String email, String password, UserRole role, Integer remainingGrades, Date createdAt) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.remainingGrades = remainingGrades;
        this.createdAt = createdAt;
    }

}