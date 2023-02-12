package kameleoon.test.user.dto;

import lombok.*;


import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime creationDate;

}
