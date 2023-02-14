package kameleoon.test.quote.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modificationDate;
    private UserShortDto author;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long likes;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long dislikes;
}
