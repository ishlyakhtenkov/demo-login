package jira.javarush.com.stub;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceStub {
    private Map<String, User> userStorage = new HashMap<>();

    {
        userStorage.put("admin@gmail.com", new User(100000L, "Admin", "admin@gmail.com", "{noop}admin", true, Set.of(Role.ADMIN, Role.USER)));
        userStorage.put("viktorwran@gmail.com", new User(100003L, "Admin", "viktorwran@gmail.com", "{noop}admin", true, Set.of(Role.ADMIN, Role.USER)));
        userStorage.put("ishlyakhtenkov@yandex.ru", new User(100002L, "Igor S", "ishlyakhtenkov@yandex.ru", "{noop}admin", true, Set.of(Role.ADMIN, Role.USER)));
        userStorage.put("user@gmail.com", new User(100001L, "User", "user@gmail.com", "{noop}password", true, Set.of(Role.USER)));
    }

    public Optional<User> getByEmail(String email) {
        return Optional.ofNullable(userStorage.get(email));
    }

    public User create(User user) {
        if (getByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("User with this email already exists");
        }
        userStorage.put(user.getEmail(), user);
        return user;
    }
}
