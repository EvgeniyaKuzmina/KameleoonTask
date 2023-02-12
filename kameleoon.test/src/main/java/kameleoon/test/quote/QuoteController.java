package kameleoon.test.quote;

import kameleoon.test.quote.dto.NewQuoteDto;
import kameleoon.test.quote.dto.QuoteDto;
import kameleoon.test.quote.dto.UpdateQuoteDto;
import kameleoon.test.user.User;
import kameleoon.test.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping
@Slf4j
@RequiredArgsConstructor
public class QuoteController {

    private static final String STANDART_SIZE = "10";

    private final QuoteService service;
    private final UserService userService;
    private final QuoteMapper mapper = Mappers.getMapper(QuoteMapper.class);


    @PostMapping(value = "/users/{userId}/quotes")
    public QuoteDto createQuote(@Valid @RequestBody NewQuoteDto newQuoteDto, @PathVariable Long userId) {
        log.info("QuoteController: createQuote — received request to create quote");
        User user = userService.getUserById(userId);
        Quote quote = mapper.toQuote(newQuoteDto);
        return mapper.quoteToQuoteDto(service.createQuote(quote, user));
    }

    @PatchMapping(value = "/users/{userId}/quotes/{quoteId}")
    public QuoteDto updateQuote(@Valid @RequestBody UpdateQuoteDto updQuoteDto,
                                @PathVariable Long userId,
                                @PathVariable Long quoteId) {
        log.info("QuoteController: updateQuote — received request to update quote");
        Quote quote = mapper.toQuote(updQuoteDto);
        return mapper.quoteToQuoteDto(service.updateQuote(quote, quoteId, userId));
    }

    @DeleteMapping(value = "/users/{userId}/quotes/{quoteId}")
    void deleteQuote(@PathVariable Long quoteId, @PathVariable Long userId) {
        service.deleteQuote(quoteId, userId);
    }

    @GetMapping(value = "quotes/{quoteId}")
    public QuoteDto getQuote(@PathVariable Long quoteId) {
        Quote quote = service.getQuote(quoteId);
        return mapper.quoteToQuoteDto(quote);
    }

    @GetMapping(value = "quotes")
    public QuoteDto getRandomQuote(@RequestParam(defaultValue = "false") Boolean random) {
        return null;
    }

    @GetMapping(value = "quotes")
    public QuoteDto getTopQuote(@RequestParam(defaultValue = STANDART_SIZE)  @Positive Integer top) {

    }

    @GetMapping(value = "quotes")
    public QuoteDto getWorseQuote(@RequestParam(defaultValue = STANDART_SIZE)  @Positive Integer worse) {

    }
}
