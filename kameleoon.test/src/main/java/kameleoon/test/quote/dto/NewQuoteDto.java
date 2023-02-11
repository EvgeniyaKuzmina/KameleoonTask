package kameleoon.test.quote.dto;

import kameleoon.test.user.User;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class NewQuoteDto {

    private String content;
    private LocalDateTime modificationDate =  LocalDateTime.now();
    private Long authorId;
}
