package kameleoon.test.quote;

import kameleoon.test.quote.model.Quote;
import kameleoon.test.quote.model.QuoteCountVotes;
import kameleoon.test.user.User;
import kameleoon.test.vote.Vote;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuoteService {

    Quote createQuote(Quote quote, User user);

    Quote updateQuote(Quote quote, Long quoteId, Long id);

    void deleteQuote(Long quoteId, Long userId);

    Quote getQuote(Long id);

    Quote getRandomQuote();

    List<QuoteCountVotes> getTopQuote(Pageable pageable );

    List<QuoteCountVotes> getWorseQuote(Integer worse);

    QuoteCountVotes addLike(Vote vote, Long quoteId, Long userId);

}
