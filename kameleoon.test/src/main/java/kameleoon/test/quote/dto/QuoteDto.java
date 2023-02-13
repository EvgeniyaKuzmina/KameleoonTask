package kameleoon.test.quote.dto;

import kameleoon.test.user.dto.UserShortDto;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuoteDto {

    private Long id;
    private String content;
    private LocalDateTime modificationDate;
    private UserShortDto author;
    @Builder.Default
    private Long likes = 0L;
    @Builder.Default
    private Long dislikes = 0L;
}
