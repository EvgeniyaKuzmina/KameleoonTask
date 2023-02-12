package kameleoon.test.quote;

import kameleoon.test.quote.dto.NewQuoteDto;
import kameleoon.test.quote.dto.QuoteDto;
import kameleoon.test.quote.dto.UpdateQuoteDto;
import kameleoon.test.quote.model.Quote;
import kameleoon.test.quote.model.QuoteCountVotes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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

    @Mapping(target = "id", source = "id")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "modificationDate", source = "modificationDate")
    @Mapping(target = "author", source = "author")
    QuoteDto toQuoteDto(Quote quote);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "modificationDate", source = "modificationDate")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "likes", source = "likes")
    @Mapping(target = "dislikes", source = "dislikes")
    QuoteDto toQuoteDto (QuoteCountVotes quoteCountVotes);

    /*@Named("authorIdToUser")
    default User authorIdToUser(Long id) {
        return reactions.stream().filter(reaction -> reaction.getReaction().equals(TypeReaction.LIKE)).count();
    }*/

   /* default User map(Long value){

    }*/
}
