package kameleoon.test.vote;

import kameleoon.test.exception.ValidationException;
import kameleoon.test.quote.model.QuoteCountVotes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    /*private final VoteRepository repository;
    @Override
    public QuoteCountVotes addLike (Vote vote, Long quoteId, Long userId) {

        try {
            repository.save(vote);
            log.info("VoteServiceImpl: addLike — lake was added");
            return repository.findQuote(quoteId);
        } catch (DataIntegrityViolationException e) {
            log.error("UserServiceImpl: createUser — fields  name, email, password can not by empty");
            throw new ValidationException("Fields  name, email, password can not by empty");
        }
    }

    @Override
    public void addDislike() {

    }*/
}
