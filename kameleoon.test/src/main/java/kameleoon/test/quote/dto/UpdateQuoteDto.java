package kameleoon.test.quote.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UpdateQuoteDto {

    @NotNull
    @Length(min = 1)
    private String content;
    private LocalDateTime modificationDate = LocalDateTime.now();
}
