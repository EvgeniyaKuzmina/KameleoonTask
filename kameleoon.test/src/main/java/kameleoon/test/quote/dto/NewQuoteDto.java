package kameleoon.test.quote.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class NewQuoteDto {

    @NotNull
    @Length(min = 1)
    private String content;
    private LocalDateTime modificationDate = LocalDateTime.now();

}
