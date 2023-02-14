package kameleoon.test.quote.vote;

import kameleoon.test.exception.SaveException;
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
            throw new SaveException("An error occurred while saving the data");
        }
    }

    @Override
    public void deleteVote(Long voteId) {
        repository.deleteById(voteId);
        log.info("VoteServiceImpl: deleteVote — vote with id {} was deleted", voteId);
    }

    @Override
    public Optional<Vote> getVoteByAuthorIdAndQuoteId(Long authorId, Long quoteId) {
        Optional<Vote> optVote = Optional.ofNullable(repository.findByAuthorIdAndQuoteId(authorId, quoteId));
        log.info("VoteServiceImpl: getVoteByAuthorIdAndQuoteId — was gotten vote by authorId and quoteId");
        return optVote;
    }
}
