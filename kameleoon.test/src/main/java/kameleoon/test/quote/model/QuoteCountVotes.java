package kameleoon.test.quote.model;

import kameleoon.test.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class QuoteCountVotes {

    private Long id;
    private String content;
    private LocalDateTime modificationDate;
    private User author;
    private Long likes;
    private Long dislikes;
}
