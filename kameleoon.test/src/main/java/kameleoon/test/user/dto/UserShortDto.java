package kameleoon.test.user.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserShortDto {

    private String name;
    private String email;

}
