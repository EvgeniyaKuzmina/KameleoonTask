package kameleoon.test.quote;

import kameleoon.test.exception.ConflictException;
import kameleoon.test.exception.ObjectNotFountException;
import kameleoon.test.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository repository;
    private final UserService userService;

    @Override
    public Quote createQuote(Quote quote) {
            quote =  repository.save(quote);
            log.info("QuoteRepository: createQuote — quote was added {}.", quote);
            return quote;
    }

    @Override
    public Quote updateQuote(Quote updQuote, Long userId) {
        validateUserIdAndQuoteId(updQuote.getId(), userId);
        Quote quote = getQuote(updQuote.getId());
        quote.setContent(updQuote.getContent());
        quote.setModificationDate(updQuote.getModificationDate());
        quote =  repository.save(quote);
        log.info("QuoteRepository: updateQuote — quote was updated {}.", quote);
        return quote;
    }

    @Override
    public void deleteQuote(Long id) {
        getQuote(id);
        repository.deleteById(id);
        log.info("QuoteRepository: deleteQuote — quote with id {} was deleted", id);
    }

    @Override
    public Quote getQuote(Long id) {
        Optional<Quote> quoteOpt = repository.findById(id);
        Quote quote = quoteOpt.orElseThrow(() -> {
            log.warn("QuoteServiceImpl: getQuote — quote with id {} not exist", id);
            throw new ObjectNotFountException("Quote with id " + id + " not exist");
        });

        log.info("QuoteServiceImpl: getQuote — quote with id {} was received", id);
        return quote;
    }

    @Override
    public Quote getRandomQuote() {
        return null;
    }

    private void validateUserIdAndQuoteId(Long quoteId, Long userId) {
        userService.checkUserById(userId);
        Quote quote = getQuote(quoteId);
        if (!quote.getAuthor().getId().equals(userId)) {
            log.error("QuoteServiceImpl: validateUserIdAndQuoteId — user with id {} does not author of quote with id {}.", userId, quote.getId());
            throw new ConflictException(String.format("User with id %d does not author of quote with id %d.",
                    userId, quote.getId()));
        }
    }
}
