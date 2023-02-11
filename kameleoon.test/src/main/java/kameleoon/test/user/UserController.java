package kameleoon.test.user;

import kameleoon.test.user.dto.NewUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @PostMapping
    public NewUserDto createUser(@Valid @RequestBody NewUserDto newUserDto) {
        log.info("UserController: createUser — received request to create user");
        User user = mapper.dtoToUser(newUserDto);
        return mapper.userToDto(service.createUser(user));
    }
}
