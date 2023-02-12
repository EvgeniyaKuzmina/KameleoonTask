package kameleoon.test.vote;

import kameleoon.test.quote.model.QuoteCountVotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("select new kameleoon.test.quote.model.QuoteCountVotes(q.id, q.content, q.modificationDate,  q.author, count(v.like), count(v.dislike)) " +
            "from Quote as q "+
            "left join Vote as v on q.id = v.quote.id "+
            "GROUP BY q.id "+
            "HAVING q.id = ?1 " +
            "ORDER BY count(v.dislike) desc")
    @Transactional
    QuoteCountVotes findQuote(Long quoteId);
}
