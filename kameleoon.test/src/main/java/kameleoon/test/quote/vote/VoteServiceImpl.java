package kameleoon.test.quote.vote;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteRepository repository;

    @Override
    public Vote saveVote(Vote newVote) {
        try {
            newVote = repository.save(newVote);
            log.info("VoteServiceImpl: saveVote — vote was added");
            return newVote;
        } catch (DataIntegrityViolationException e) {
            log.error("VoteServiceImpl: saveVote — an error occurred while saving the data");
            throw new RuntimeException("An error occurred while saving the data"); // TODO
        }
    }

    @Override
    public void deleteVote(Long voteId) {
        repository.deleteById(voteId);
        log.info("VoteServiceImpl: deleteVote — vote with id {} was deleted", voteId);
    }

    @Override
    public void changeLikeDislike(Boolean like, Boolean dislike, Long voteId) {
        repository.updateLikeDislike(like, dislike, voteId);
        log.info("VoteServiceImpl: changeLikeDislike — was changed like/dislike");
    }

    @Override
    public Optional<Vote> getVoteByAuthorIdAndQuoteId(Long authorId, Long quoteId) {
        Optional<Vote> optVote = Optional.ofNullable(repository.findByAuthorIdAndQuoteId(authorId, quoteId));
        log.info("VoteServiceImpl: getVoteByAuthorIdAndQuoteId — gotten vote by authorId and quoteId");
        return optVote;
    }
}
