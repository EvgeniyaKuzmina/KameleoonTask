package kameleoon.test.quote;


import kameleoon.test.quote.model.Quote;
import kameleoon.test.quote.model.QuoteCountVotes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {


    @Query("select new kameleoon.test.quote.model.QuoteCountVotes(q.id, q.content, q.modificationDate,  q.author, count(v.like), count(v.dislike)) " +
            "from Quote as q "+
            "left join Vote as v on q.id = v.quote.id "+
            "GROUP BY q.id "+
            "ORDER BY count(v.like) desc")
    /*@Query(value = "select q.ID, q.CONTENT, q.MODIFICATION_DATE,  q.AUTHOR_ID, count(v.LIKES) as count_likes, count(v.DISLIKES) as count_dislikes "+
            "from QUOTES as q left join VOTES as v on q.id = v.QUOTES_ID " +
            "GROUP BY q.id " +
            "ORDER BY count_likes desc " +
            "LIMIT 10;", nativeQuery = true)*/
    @Transactional
    List<QuoteCountVotes> findTopQuote(Pageable pageable);

    /*@Query(value = "select q.ID, q.CONTENT, q.MODIFICATION_DATE, q.AUTHOR_ID, count(v.LIKES) as count_likes, count(v.DISLIKES) as count_dislikes "+
            "from QUOTES as q left join VOTES as v on q.id = v.QUOTES_ID " +
            "GROUP BY q.id " +
            "ORDER BY count_dislikes desc " +
            "LIMIT 10;", nativeQuery = true)*/
    @Query("select new kameleoon.test.quote.model.QuoteCountVotes(q.id, q.content, q.modificationDate,  q.author, count(v.like), count(v.dislike)) " +
                  "from Quote as q "+
                  "left join Vote as v on q.id = v.quote.id "+
                  "GROUP BY q.id "+
                  "ORDER BY count(v.dislike) desc")
    @Transactional
    List<QuoteCountVotes> findWorseQuote(Integer worse);

    @Query("select new kameleoon.test.quote.model.QuoteCountVotes(q.id, q.content, q.modificationDate,  q.author, count(v.like), count(v.dislike)) " +
            "from Quote as q "+
            "left join Vote as v on q.id = v.quote.id "+
            "GROUP BY q.id "+
            "HAVING q.id = ?1 " +
            "ORDER BY count(v.dislike) desc")
    @Transactional
    QuoteCountVotes findQuote(Long quoteId);

}
