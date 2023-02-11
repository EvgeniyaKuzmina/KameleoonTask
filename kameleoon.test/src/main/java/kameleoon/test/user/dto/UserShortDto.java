package kameleoon.test.user.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserShortDto {
    private String name;
    private String email;

}
