package kameleoon.test.quote.vote;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface VoteMapper {

    Vote toVote(VoteDto voteDto);


}
