package kameleoon.test.quote;

import kameleoon.test.quote.model.Quote;
import kameleoon.test.quote.model.QuoteCountVotes;
import kameleoon.test.quote.model.QuoteRandom;
import kameleoon.test.quote.vote.Vote;
import kameleoon.test.user.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuoteService {

    Quote createQuote(Quote quote, User user);

    Quote updateQuote(Quote quote, Long quoteId, Long id);

    void deleteQuote(Long quoteId, Long userId);

    QuoteCountVotes getQuoteById(Long id);

    QuoteRandom getRandomQuote();

    List<QuoteCountVotes> getTopQuote(Pageable pageable);

    List<QuoteCountVotes> getWorseQuote(Pageable pageable);

    QuoteCountVotes addLikeOrDislike(Vote vote, Long quoteId, Long userId);



}
