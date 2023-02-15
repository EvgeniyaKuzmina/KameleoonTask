package kameleoon.test.quote.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RandomQuoteDto {

    Long id;
    private String content;
}
