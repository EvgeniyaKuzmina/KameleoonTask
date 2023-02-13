package kameleoon.test.quote;

import kameleoon.test.quote.dto.NewQuoteDto;
import kameleoon.test.quote.dto.QuoteDto;
import kameleoon.test.quote.dto.RandomQuoteDto;
import kameleoon.test.quote.dto.UpdateQuoteDto;
import kameleoon.test.quote.model.Quote;
import kameleoon.test.quote.model.QuoteCountVotes;
import kameleoon.test.quote.model.QuoteRandom;
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
    QuoteDto toQuoteDto(QuoteCountVotes quoteCountVotes);

    @Mapping(target = "content", source = "content")
    RandomQuoteDto toQuoteDto(QuoteRandom quote);

}
