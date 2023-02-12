package kameleoon.test.quote;

import kameleoon.test.exception.ConflictException;
import kameleoon.test.exception.ObjectNotFountException;
import kameleoon.test.exception.ValidationException;
import kameleoon.test.quote.model.Quote;
import kameleoon.test.quote.model.QuoteCountVotes;
import kameleoon.test.user.User;
import kameleoon.test.user.UserService;
import kameleoon.test.vote.Vote;
import kameleoon.test.vote.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {
    private final VoteRepository voteRepository;

    private final QuoteRepository repository;
    private final UserService userService;


    @Override
    public Quote createQuote(Quote quote, User user) {
        quote.setAuthor(user);
            quote =  repository.save(quote);
            log.info("QuoteRepository: createQuote — quote was added {}.", quote);
            return quote;
    }

    @Override
    public Quote updateQuote(Quote updQuote, Long quoteId, Long userId) {
        validateUserIdAndQuoteId(quoteId, userId);
        Quote quote = getQuote(quoteId);
        quote.setContent(updQuote.getContent());
        quote.setModificationDate(updQuote.getModificationDate());
        quote =  repository.save(quote);
        log.info("QuoteRepository: updateQuote — quote was updated {}.", quote);
        return quote;
    }

    @Override
    public void deleteQuote(Long quoteId, Long userId) {
        validateUserIdAndQuoteId(quoteId, userId);
        repository.deleteById(quoteId);
        log.info("QuoteRepository: deleteQuote — quote with id {} was deleted", quoteId);
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

    @Override
    public List<QuoteCountVotes> getTopQuote(Pageable pageable) {
        return repository.findTopQuote(pageable);
    }

    @Override
    public List<QuoteCountVotes> getWorseQuote(Integer worse) {
        return repository.findWorseQuote(worse);
    }

    @Override
    public QuoteCountVotes addLike (Vote vote, Long quoteId, Long userId) {

        User user = userService.getUserById(userId);
        Quote quote = getQuote(quoteId);
        vote.setQuote(quote);
        vote.setAuthor(user);
        try {
            voteRepository.save(vote);
            log.info("VoteServiceImpl: addLike — lake was added");
            return repository.findQuote(quoteId);
        } catch (DataIntegrityViolationException e) {
            log.error("UserServiceImpl: createUser — fields  name, email, password can not by empty");
            throw new ValidationException("Fields  name, email, password can not by empty");
        }
    }

    private void validateUserIdAndQuoteId(Long quoteId, Long userId) {
        userService.getUserById(userId);
        Quote quote = getQuote(quoteId);
        if (!quote.getAuthor().getId().equals(userId)) {
            log.error("QuoteServiceImpl: validateUserIdAndQuoteId — user with id {} does not author of quote with id {}.", userId, quote.getId());
            throw new ConflictException(String.format("User with id %d does not author of quote with id %d.",
                    userId, quote.getId()));
        }
    }
}
