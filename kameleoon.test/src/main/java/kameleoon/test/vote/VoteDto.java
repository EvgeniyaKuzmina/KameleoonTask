package kameleoon.test.vote;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class VoteDto {

    private Boolean like;
    private Boolean dislike;

}
