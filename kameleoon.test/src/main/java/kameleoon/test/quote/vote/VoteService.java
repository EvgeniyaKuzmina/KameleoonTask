package kameleoon.test.quote.vote;

import java.util.Optional;

public interface VoteService {

    Vote saveVote(Vote newVote);

    void deleteVote(Long voteId);

    void changeLikeDislike(Boolean like, Boolean dislike, Long voteId);

    Optional<Vote> getVoteByAuthorIdAndQuoteId(Long authorId, Long quoteId);
}
