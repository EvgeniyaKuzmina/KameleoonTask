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
    public QuoteCountVotes getQuoteById(Long id) {
        QuoteCountVotes quoteCountVotes = getQuoteCountVotes(id);
        log.info("QuoteServiceImpl: getQuoteById — quote with votes with id {} was received", id);
        return quoteCountVotes;
    }

    @Override
    public QuoteRandom getRandomQuote() {
        final Random random = new Random();
        List<QuoteRandom> quoteRandomList = repository.findAllQuotes();
        if (quoteRandomList.isEmpty()) {
            log.warn("QuoteServiceImpl: getRandomQuote — on the server no any Quote");
            throw new ObjectNotFountException("On the server no any Quote");
        }
        int rndIndex = random.nextInt(quoteRandomList.size());
        QuoteRandom quoteRandom = quoteRandomList.get(rndIndex);
        log.info("QuoteServiceImpl: getRandomQuote — random Quote was received");
        return quoteRandom;
    }

    @Override
    public List<QuoteCountVotes> getTopQuote(Pageable pageable) {
        List<QuoteCountVotes> quoteCountVotes = repository.findTopQuotes(pageable);
        if (quoteCountVotes.isEmpty()) {
            log.warn("QuoteServiceImpl: getTopQuote — on the server no any Quote");
            throw new ObjectNotFountException("On the server no any Quote");
        }
        log.info("QuoteServiceImpl: getTopQuote — top Quotes was received");
        return quoteCountVotes;
    }

    @Override
    public List<QuoteCountVotes> getWorseQuote(Pageable pageable) {
        List<QuoteCountVotes> quoteCountVotes = repository.findWorseQuotes(pageable);
        if (quoteCountVotes.isEmpty()) {
            log.warn("QuoteServiceImpl: getWorseQuote — on the server no any Quote");
            throw new ObjectNotFountException("On the server no any Quote");
        }
        log.info("QuoteServiceImpl: getWorseQuote — worse Quotes was received");
        return quoteCountVotes;
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
        QuoteCountVotes quoteCountVotes = getQuoteCountVotes(vote.getQuote().getId());
        log.info("QuoteServiceImpl: addLikeOrDislike — like/dislike was added");
        return quoteCountVotes;
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

    private QuoteCountVotes getQuoteCountVotes(Long id) {
        Optional<QuoteCountVotes> quoteCountVotes = Optional.ofNullable(repository.findQuoteWithVotes(id));
        QuoteCountVotes quote = quoteCountVotes.orElseThrow(() -> {
            log.warn("QuoteServiceImpl: getQuoteCountVotes — quote with id {} not exist", id);
            throw new ObjectNotFountException("Quote with id " + id + " not exist");
        });
        log.info("QuoteServiceImpl: getQuote — quote with id {} was received", id);
        return quote;
    }

    private Quote getQuote(Long id) {
        Optional<Quote> quoteOpt = repository.findById(id);
        Quote quote = quoteOpt.orElseThrow(() -> {
            log.warn("QuoteServiceImpl: getQuote — quote with id {} not exist", id);
            throw new ObjectNotFountException("Quote with id " + id + " not exist");
        });
        log.info("QuoteServiceImpl: getQuote — quote with id {} was received", id);
        return quote;
    }
}
