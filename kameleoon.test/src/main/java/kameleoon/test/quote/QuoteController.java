package kameleoon.test.quote;

import kameleoon.test.quote.dto.NewQuoteDto;
import kameleoon.test.quote.dto.QuoteDto;
import kameleoon.test.quote.dto.UpdateQuoteDto;
import kameleoon.test.quote.model.Quote;
import kameleoon.test.quote.model.QuoteCountVotes;
import kameleoon.test.user.User;
import kameleoon.test.user.UserService;
import kameleoon.test.vote.Vote;
import kameleoon.test.vote.VoteDto;
import kameleoon.test.vote.VoteMapper;
import kameleoon.test.vote.VoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@Slf4j
@RequiredArgsConstructor
public class QuoteController {

    private static final String STANDART_SIZE = "10";

    private final QuoteService service;
    private final UserService userService;
    private final VoteService voteService;
    private final QuoteMapper mapper = Mappers.getMapper(QuoteMapper.class);

    private final VoteMapper mapperVote = Mappers.getMapper(VoteMapper.class);


    @PostMapping(value = "/users/{userId}/quotes")
    public QuoteDto createQuote(@Valid @RequestBody NewQuoteDto newQuoteDto, @PathVariable Long userId) {
        log.info("QuoteController: createQuote — received request to create quote");
        User user = userService.getUserById(userId);
        Quote quote = mapper.toQuote(newQuoteDto);
        return mapper.toQuoteDto(service.createQuote(quote, user));
    }

    @PatchMapping(value = "/users/{userId}/quotes/{quoteId}")
    public QuoteDto updateQuote(@Valid @RequestBody UpdateQuoteDto updQuoteDto,
                                @PathVariable Long userId,
                                @PathVariable Long quoteId) {
        log.info("QuoteController: updateQuote — received request to update quote");
        Quote quote = mapper.toQuote(updQuoteDto);
        return mapper.toQuoteDto(service.updateQuote(quote, quoteId, userId));
    }

    @DeleteMapping(value = "/users/{userId}/quotes/{quoteId}")
    void deleteQuote(@PathVariable Long quoteId, @PathVariable Long userId) {
        service.deleteQuote(quoteId, userId);
    }

    @GetMapping(value = "quotes/{quoteId}")
    public QuoteDto getQuote(@PathVariable Long quoteId) {
        Quote quote = service.getQuote(quoteId);
        return mapper.toQuoteDto(quote);
    }

    @GetMapping(value = "quotes?random")
    public QuoteDto getRandomQuote(@RequestParam(defaultValue = "false") Boolean random) {
        return null;
    }

    @GetMapping(value = "quotes/top")
    public List<QuoteDto> getTopQuote(@RequestParam(defaultValue = STANDART_SIZE)  @Positive Integer top) {
        Pageable pageable = PageRequest.ofSize(top);
        List<QuoteCountVotes> quoteCountVotes = service.getTopQuote(pageable);
        return quoteCountVotes.stream().map(mapper::toQuoteDto).collect(Collectors.toList());

    }

    @GetMapping(value = "quotes/worse")
    public List<QuoteDto> getWorseQuote(@RequestParam(defaultValue = STANDART_SIZE)  @Positive Integer worse) {
        List<QuoteCountVotes> quoteCountVotes = service.getWorseQuote(worse);
        return quoteCountVotes.stream().map(mapper::toQuoteDto).collect(Collectors.toList());

    }

    @PostMapping(value = "/users/{userId}/quotes/{quoteId}")
    public QuoteDto addLike(@Valid @RequestBody VoteDto voteDto, @PathVariable Long quoteId, @PathVariable Long userId) {
        log.info("VoteController: addLike — received request to add like");
        Vote vote = mapperVote.toVote(voteDto);
        return mapper.toQuoteDto(service.addLike(vote, quoteId, userId));
    }
}
