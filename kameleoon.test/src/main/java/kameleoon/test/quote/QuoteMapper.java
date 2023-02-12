package kameleoon.test.quote;

import kameleoon.test.quote.dto.NewQuoteDto;
import kameleoon.test.quote.dto.QuoteDto;
import kameleoon.test.quote.dto.UpdateQuoteDto;
import kameleoon.test.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface QuoteMapper {

    @Mapping(target = "content", source = "content")
    @Mapping(target = "modificationDate", source = "modificationDate")
    //@Mapping(target = "author", source = "authorId", qualifiedByName = "authorIdToUser")
    Quote toQuote(NewQuoteDto newQuoteDto);

    //@Mapping(target = "id", source = "id")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "modificationDate", source = "modificationDate")
    Quote toQuote(UpdateQuoteDto newQuoteDto);

    QuoteDto quoteToQuoteDto(Quote quote);

    /*@Named("authorIdToUser")
    default User authorIdToUser(Long id) {
        return reactions.stream().filter(reaction -> reaction.getReaction().equals(TypeReaction.LIKE)).count();
    }*/

   /* default User map(Long value){

    }*/
}
