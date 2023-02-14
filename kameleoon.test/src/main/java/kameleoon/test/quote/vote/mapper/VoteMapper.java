package kameleoon.test.quote.vote.mapper;

import kameleoon.test.quote.vote.Vote;
import kameleoon.test.quote.vote.VoteDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface VoteMapper {

    Vote toVote(VoteDto voteDto);

}
