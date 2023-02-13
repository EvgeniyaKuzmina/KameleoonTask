package kameleoon.test.user;

import kameleoon.test.exception.ConflictException;
import kameleoon.test.exception.ObjectNotFountException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User createUser(User user) {
        try {
            user = repository.save(user);
            log.info("UserServiceImpl: createUser — user was added {}.", user);
            return user;
        } catch (DataIntegrityViolationException e) {
            log.error("UserServiceImpl: createUser — user with email {} already exist", user.getEmail());
            throw new ConflictException(String.format("User with email %s already exist",
                    user.getEmail()));
        }
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> userOpt = repository.findById(id);
        User user = userOpt.orElseThrow(() -> {
            log.warn("UserServiceImpl: getUserById — user with id {} not exist", id);
            throw new ObjectNotFountException("User with id " + id + " not exist");
        });

        log.info("UserServiceImpl: getUserById — user with id {} was received", id);
        return user;

    }
}
