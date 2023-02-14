package kameleoon.test.quote;

import kameleoon.test.exception.ValidationException;
import kameleoon.test.quote.dto.NewQuoteDto;
import kameleoon.test.quote.dto.QuoteDto;
import kameleoon.test.quote.dto.RandomQuoteDto;
import kameleoon.test.quote.dto.UpdateQuoteDto;
import kameleoon.test.quote.mapper.QuoteMapper;
import kameleoon.test.quote.model.Quote;
import kameleoon.test.quote.model.QuoteCountVotes;
import kameleoon.test.quote.vote.Vote;
import kameleoon.test.quote.vote.VoteDto;
import kameleoon.test.quote.vote.mapper.VoteMapper;
import kameleoon.test.user.User;
import kameleoon.test.user.UserService;
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

    private static final String STANDARD_SIZE = "10";

    private final QuoteService service;
    private final UserService userService;
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
        log.info("QuoteController: deleteQuote — received request to delete Quote with id {}", quoteId);
        service.deleteQuote(quoteId, userId);
    }

    @GetMapping(value = "quotes/{quoteId}")
    public QuoteDto getQuote(@PathVariable Long quoteId) {
        log.info("QuoteController: getQuote — received request to get Quote with id {}", quoteId);
        QuoteCountVotes quote = service.getQuoteById(quoteId);
        return mapper.toQuoteDto(quote);
    }

    @GetMapping(value = "quotes/random")
    public RandomQuoteDto getRandomQuote() {
        log.info("QuoteController: getRandomQuote — received request to get random quote");
        return mapper.toQuoteDto(service.getRandomQuote());
    }

    @GetMapping(value = "quotes/top")
    public List<QuoteDto> getTopQuote(@RequestParam(defaultValue = STANDARD_SIZE) @Positive Integer top) {
        log.info("QuoteController: getTopQuote — received request to get top Quote");
        Pageable pageable = PageRequest.ofSize(top);
        List<QuoteCountVotes> quoteCountVotes = service.getTopQuote(pageable);
        return quoteCountVotes.stream().map(mapper::toQuoteDto).collect(Collectors.toList());

    }

    @GetMapping(value = "quotes/worse")
    public List<QuoteDto> getWorseQuote(@RequestParam(defaultValue = STANDARD_SIZE) @Positive Integer worse) {
        log.info("QuoteController: getWorseQuote — received request to get worse Quote");
        Pageable pageable = PageRequest.ofSize(worse);
        List<QuoteCountVotes> quoteCountVotes = service.getWorseQuote(pageable);
        return quoteCountVotes.stream().map(mapper::toQuoteDto).collect(Collectors.toList());

    }

    @PostMapping(value = "/users/{userId}/quotes/{quoteId}")
    public QuoteDto addLikeOrDislike(@Valid @RequestBody VoteDto voteDto, @PathVariable Long quoteId, @PathVariable Long userId) {
        log.info("QuoteController: addLikeOrDislike — received request to add like or dislike");
        if (voteDto.getLike() == 1 && voteDto.getDislike() == 1) {
            throw new ValidationException("Not possible add like and dislike in the same time");
        }
        Vote vote = mapperVote.toVote(voteDto);
        return mapper.toQuoteDto(service.addLikeOrDislike(vote, quoteId, userId));
    }

}
