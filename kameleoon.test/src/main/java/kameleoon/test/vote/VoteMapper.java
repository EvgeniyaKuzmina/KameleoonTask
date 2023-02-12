package kameleoon.test.vote;

import kameleoon.test.user.User;
import kameleoon.test.user.dto.NewUserDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface VoteMapper {

    Vote toVote(VoteDto voteDto);


}
