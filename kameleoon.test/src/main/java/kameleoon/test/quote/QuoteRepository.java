package kameleoon.test.quote;


import kameleoon.test.quote.model.Quote;
import kameleoon.test.quote.model.QuoteCountVotes;
import kameleoon.test.quote.model.QuoteRandom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {


    @Query("select new kameleoon.test.quote.model.QuoteCountVotes(q.id, q.content, q.modificationDate,  q.author, " +
            "sum(v.like), sum(v.dislike)) " +
            "from Quote as q " +
            "left join Vote as v on q.id = v.quote.id " +
            "GROUP BY q.id " +
            "ORDER BY sum(v.like) desc")
    @Transactional
    List<QuoteCountVotes> findTopQuotes(Pageable pageable);

    @Query("select new kameleoon.test.quote.model.QuoteCountVotes(q.id, q.content, q.modificationDate,  q.author, " +
            "sum(v.like), sum(v.dislike)) " +
            "from Quote as q " +
            "left join Vote as v on q.id = v.quote.id " +
            "GROUP BY q.id " +
            "ORDER BY sum(v.dislike) desc")
    @Transactional
    List<QuoteCountVotes> findWorseQuotes(Pageable pageable);

    @Query("select new kameleoon.test.quote.model.QuoteCountVotes(q.id, q.content, q.modificationDate,  q.author, " +
            "sum(v.like), sum(v.dislike)) " +
            "from Quote as q " +
            "left join Vote as v on q.id = v.quote.id " +
            "GROUP BY q.id " +
            "HAVING q.id = ?1")
    @Transactional
    QuoteCountVotes findQuoteWithVotes(Long quoteId);

    @Query("select new kameleoon.test.quote.model.QuoteRandom(q.id, q.content) " +
            "from Quote as q")
    @Transactional
    List<QuoteRandom> findAllQuotes();

}
