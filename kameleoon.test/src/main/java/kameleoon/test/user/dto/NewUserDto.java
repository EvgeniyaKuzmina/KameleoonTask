package kameleoon.test.user.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class NewUserDto {

    //private Long id;
    @Length(min = 1, max = 300)
    private String name;
    @Email
    @NotNull
    @Length(min = 1, max = 100)
    private String email;
    @NotNull
    @Length(min = 1, max = 20)
    private String password;
    private final LocalDateTime creationDate = LocalDateTime.now();
}
