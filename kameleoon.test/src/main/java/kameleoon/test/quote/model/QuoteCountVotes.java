package kameleoon.test.quote.model;

import kameleoon.test.user.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class QuoteCountVotes {

    private Long id;
    private String content;
    private LocalDateTime modificationDate;
    private User author;
    @Builder.Default
    private Long likes = 0L;
    @Builder.Default
    private Long dislikes = 1L;
}
