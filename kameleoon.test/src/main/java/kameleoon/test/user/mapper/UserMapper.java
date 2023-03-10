package kameleoon.test.user.mapper;


import kameleoon.test.user.User;
import kameleoon.test.user.dto.NewUserDto;
import kameleoon.test.user.dto.UserDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface  UserMapper {
    User dtoToUser(NewUserDto newUserDto);
    UserDto userToDto(User user);
}

