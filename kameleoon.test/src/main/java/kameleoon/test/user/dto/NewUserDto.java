package kameleoon.test.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;


import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class NewUserDto {

    private final LocalDateTime creationDate = LocalDateTime.now();
    @NotNull
    @NotBlank
    @Length(min = 1, max = 300)
    private String name;
    @Email
    @NotNull
    @Length(min = 1, max = 100)
    private String email;
    @NotNull
    @Length(min = 1, max = 20)
    private String password;
}
