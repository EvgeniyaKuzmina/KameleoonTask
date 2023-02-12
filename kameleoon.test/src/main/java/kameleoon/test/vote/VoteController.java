/*
package kameleoon.test.vote;

import kameleoon.test.quote.QuoteMapper;
import kameleoon.test.quote.dto.QuoteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/users/{userId}/quotes/{quoteId}")
@Slf4j
@RequiredArgsConstructor
public class VoteController {
    private final VoteService service;

    private final VoteMapper mapper = Mappers.getMapper(VoteMapper.class);
    private final QuoteMapper quoteMapper = Mappers.getMapper(QuoteMapper.class);


    @PostMapping
    public QuoteDto addLike(@Valid @RequestBody VoteDto voteDto, @PathVariable Long quoteId, @PathVariable Long userId) {
        log.info("VoteController: addLike — received request to add like");
        Vote vote = mapper.toVote(voteDto);
        return quoteMapper.toQuoteDto(service.addLike(vote, quoteId, userId));
    }
}
*/
