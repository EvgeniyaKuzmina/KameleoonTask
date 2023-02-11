package kameleoon.test.quote.dto;

import kameleoon.test.user.dto.UserShortDto;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UpdateQuoteDto {
    private Long id;
    private String content;
    private LocalDateTime modificationDate =  LocalDateTime.now();
}
