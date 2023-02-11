package kameleoon.test.user;

public interface UserService {
    User createUser(User user);

    User checkUserById(Long id);
}
