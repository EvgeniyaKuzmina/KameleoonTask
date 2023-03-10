package kameleoon.test.quote.vote;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class VoteDto {

    @NotNull
    @Builder.Default
    private Integer like = 0;
    @NotNull
    @Builder.Default
    private Integer dislike = 0;
    private LocalDateTime modificationDate = LocalDateTime.now();


    public void setLike(Boolean like) {
        this.like = Boolean.TRUE.equals(like) ? 1 : 0;
    }

    public void setDislike(Boolean dislike) {
        this.dislike = Boolean.TRUE.equals(dislike) ? 1 : 0;
    }
}
