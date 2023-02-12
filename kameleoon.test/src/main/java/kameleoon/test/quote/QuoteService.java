package kameleoon.test.quote;

import kameleoon.test.user.User;
import org.springframework.stereotype.Service;

public interface QuoteService {

    Quote createQuote(Quote quote, User user);

    Quote updateQuote(Quote quote, Long quoteId, Long id);

    void deleteQuote(Long quoteId, Long userId);

    Quote getQuote(Long id);

    Quote getRandomQuote();

    Quote getTopQuote(Integer top);

    Quote getWorseQuote(Integer worse);

}
