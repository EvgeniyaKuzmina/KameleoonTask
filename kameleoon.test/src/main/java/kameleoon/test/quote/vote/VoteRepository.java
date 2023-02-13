package kameleoon.test.quote.vote;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Vote findByAuthorIdAndQuoteId(Long authorId, Long quoteId);

    @Query(value = "update VOTES set LIKES = ?1, DISLIKES = ?2 where id = ?3", nativeQuery = true)
    @Modifying
    @Transactional
    void updateLikeDislike(Boolean like, Boolean dislike, Long id);
}
