package kameleoon.test.quote.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

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
