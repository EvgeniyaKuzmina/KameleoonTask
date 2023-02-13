package kameleoon.test.quote;

import kameleoon.test.exception.ConflictException;
import kameleoon.test.exception.ObjectNotFountException;
import kameleoon.test.quote.model.Quote;
import kameleoon.test.quote.model.QuoteCountVotes;
import kameleoon.test.quote.model.QuoteRandom;
import kameleoon.test.quote.vote.Vote;
import kameleoon.test.quote.vote.VoteService;
import kameleoon.test.user.User;
import kameleoon.test.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;


@Slf4j
@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository repository;
    private final UserService userService;
    private final VoteService voteService;


    @Override
    public Quote createQuote(Quote quote, User user) {
        quote.setAuthor(user);
        quote = repository.save(quote);
        log.info("QuoteRepository: createQuote — quote was added {}.", quote);
        return quote;
    }

    @Override
    public Quote updateQuote(Quote updQuote, Long quoteId, Long userId) {
        validateUserIdAndQuoteId(quoteId, userId);
        Quote quote = getQuote(quoteId);
        quote.setContent(updQuote.getContent());
        quote.setModificationDate(updQuote.getModificationDate());
        quote = repository.save(quote);
        log.info("QuoteRepository: updateQuote — quote was updated {}.", quote);
        return quote;
    }

    @Override
    public void deleteQuote(Long quoteId, Long userId) {
        validateUserIdAndQuoteId(quoteId, userId);
        repository.deleteById(quoteId);
        log.info("QuoteRepository: deleteVote — quote with id {} was deleted", quoteId);
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
    public QuoteRandom getRandomQuote() {
        List<QuoteRandom> quoteRandom = repository.findIdsQuotes();
        if (quoteRandom.isEmpty()) {
            throw new ObjectNotFountException("On the server no any Quote");
        }
        int rndIndex = new Random().nextInt(quoteRandom.size());
        return quoteRandom.get(rndIndex);
    }

    @Override
    public List<QuoteCountVotes> getTopQuote(Pageable pageable) {
        return repository.findTopQuotes(pageable);
    }

    @Override
    public List<QuoteCountVotes> getWorseQuote(Pageable pageable) {
        return repository.findWorseQuotes(pageable);
    }

    @Override
    public QuoteCountVotes addLikeOrDislike(Vote newVote, Long quoteId, Long userId) {
        User user = userService.getUserById(userId);
        Quote quote = getQuote(quoteId);
        Optional<Vote> voteOpt = voteService.getVoteByAuthorIdAndQuoteId(userId, quoteId);
        if (voteOpt.isPresent()) {
            Vote vote = voteOpt.get();
            voteService.deleteVote(vote.getId());
        }
        newVote.setQuote(quote);
        newVote.setAuthor(user);
        Vote vote = voteService.saveVote(newVote);
        return repository.findQuoteWithVotes(vote.getQuote().getId());
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
