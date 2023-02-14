package kameleoon.test.user;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 300)
    private String name;
    @Email
    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;
    @Column(name = "password", nullable = false, length = 20)
    private String password;
    @Column(name = "date_of_creation", nullable = false)
    private LocalDateTime creationDate;

}

