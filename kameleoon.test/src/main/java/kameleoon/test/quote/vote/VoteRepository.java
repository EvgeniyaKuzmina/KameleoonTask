package kameleoon.test.quote.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Vote findByAuthorIdAndQuoteId(Long authorId, Long quoteId);
}
