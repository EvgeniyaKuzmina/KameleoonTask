package kameleoon.test.quote;

import kameleoon.test.user.User;

public interface QuoteService {

    Quote createQuote(Quote quote);

    Quote updateQuote(Quote quote, Long id);

    void deleteQuote(Long id);

    Quote getQuote(Long id);

    Quote getRandomQuote();

}
